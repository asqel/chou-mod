package fr.asqel.chou.utils;

import java.util.List;

import com.google.common.primitives.Bytes;

import fr.asqel.chou.blocks.computer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.redstone.Redstone;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class Interpreter {

    public static final int OPC_MOV = 0;
    public static final int OPC_CMP = 1;
    public static final int OPC_SUB = 2;
    public static final int OPC_ADD = 3;
    public static final int OPC_MUL = 4;
    public static final int OPC_DIV = 5;
    public static final int OPC_MOD = 6;
    public static final int OPC_OUT = 7;
    public static final int OPC_IN = 8;
    public static final int OPC_HLT = 9;
    public static final int OPC_JMP = 10;
    public static final int OPC_JMPIF = 11; // + [0; 7]
    public static final int OPC_SLEEP = 18;

    public enum InstInfo {
        mov(OPC_MOV, 1, 1 | 2),
        cmp(OPC_CMP, 1, 1 | 2),
        sub(OPC_SUB, 1, 1 | 2),
        add(OPC_ADD, 1, 1 | 2),
        mul(OPC_MUL, 1, 1 | 2),
        div(OPC_DIV, 1, 1 | 2),
        mod(OPC_MOD, 1, 1 | 2),
        out(OPC_OUT, 1, 1 | 2), // write(port=a, val=b)
        in(OPC_IN, 1, 1 | 2), // a = read(b)
        hlt(OPC_HLT),
        ;
        
        public final int opcode;
        public final int[] args;
        InstInfo(int opcode) {
            this.opcode = opcode;
            this.args = new int[0];
        }
        
        InstInfo(int opcode, int arg_type_a) {
            this.opcode = opcode;
            this.args = new int[1];
            this.args[0] = arg_type_a;
        }

        InstInfo(int opcode, int arg_type_a, int arg_type_b) {
            this.opcode = opcode;
            this.args = new int[2];
            this.args[0] = arg_type_a;
            this.args[1] = arg_type_b;
        }
    }

    public static final int OPC_SHIFT = 16;
    public static final int ARG_A_SHIFT = 8;
    public static final int ARG_B_SHIFT = 0;
    public static final int ARG_TYPE_SHIFT = 7;

    public static int make_inst(int opc, int a, int b) {
        return (opc << OPC_SHIFT) | (a << ARG_A_SHIFT) | (b << ARG_B_SHIFT);
    }

    public static int make_arg_reg(int reg_idx) {
        return reg_idx | (1 << ARG_TYPE_SHIFT);
    }

    public static int make_arg_int(int reg_idx) {
        return reg_idx;
    }

    public static int make_jump(int addr) {
        return (OPC_JMP << OPC_SHIFT) | addr;
    }

    public static int make_jump_cond(int addr, int mod) {
        return ((OPC_JMPIF + mod) << OPC_SHIFT) | addr;
    }

    public static int make_sleep(int time) {
        return (OPC_SLEEP << OPC_SHIFT) | time;
    }

    public static int FLAG_EQ = (1 << 0);
    public static int FLAG_LE = (1 << 1);
    public static int FLAG_GE = (1 << 2);
    public static int FLAG_AND = (1 << 3);
    public static int FLAG_OR = (1 << 4);
    public static int FLAG_SLEEP = (1 << 5);
    public static int FLAG_HALT = (1 << 6);

    public static int INST_PER_TICK = 20;

    private int[] instructions = new int[0];
    private int r_ip = 0;
    private int r_flags = 0;
    private byte[] registers = new byte[64];
    private int sleep_tick = 0;

    public byte[] ports = new byte[4];
    public byte[] ports_in = new byte[4];

    public Interpreter() {
    }

    public void reset() {
        this.r_ip = 0;
        this.r_flags = 0;
        for (int i = 0; i < registers.length; i++)
            registers[i] = 0;
    }

    public void set_instructions(int[] code) {
        this.reset();
        this.instructions = code.clone();
    }

    public void set_instructions(List<Integer> lst) {
        this.reset();
        this.instructions = lst.stream().mapToInt(Integer::intValue).toArray();
    }

    public void save_nbt(ValueOutput output) {
        output.putInt("chou:sleep_tick", this.sleep_tick);
        output.putByteArray("chou:registers", this.registers);
        output.putInt("chou:r_flags", this.r_flags);
        output.putInt("chou:r_ip", this.r_ip);
        output.putIntArray("chou:instructions", this.instructions);
        output.putByteArray("chou:ports", ports);
    }

    public void read_nbt(ValueInput input) {
        this.sleep_tick = input.getIntOr("chou:sleep_tick", 0);
        this.registers = input.getOptionalByteArray("chou:registers").orElse(new byte[64]);
        this.r_flags = input.getIntOr("chou:r_flags", 0);
        this.r_ip = input.getIntOr("chou:r_ip", 0);
        this.instructions = input.getIntArray("chou:instructions").orElse(new int[0]);
        this.ports = input.getOptionalByteArray("chou:ports").orElse(new byte[4]);
    }

    public void do_jump(int opcode, int offset) {
        int will_jump = 0;

        //String[] mods = {"eq", "neq", "le", "leq", "ge", "geq", "and", "or"};
        if (opcode == OPC_JMP)
            will_jump = 1;
        else if (opcode == OPC_JMPIF + 0) // eq
            will_jump = this.r_flags & FLAG_EQ;
        else if (opcode == OPC_JMPIF + 1) // neq
            will_jump = (this.r_flags & FLAG_EQ) == 0 ? 1 : 0;
        else if (opcode == OPC_JMPIF + 2) // le
            will_jump = this.r_flags & FLAG_LE;
        else if (opcode == OPC_JMPIF + 3) // leq
            will_jump = this.r_flags & (FLAG_LE | FLAG_EQ);
        else if (opcode == OPC_JMPIF + 4) // ge
            will_jump = this.r_flags & FLAG_GE;
        else if (opcode == OPC_JMPIF + 5) // geq
            will_jump = this.r_flags & (FLAG_GE | FLAG_EQ);
        else if (opcode == OPC_JMPIF + 6) // and
            will_jump = this.r_flags & FLAG_AND;
        else if (opcode == OPC_JMPIF + 7) // and
            will_jump = this.r_flags & FLAG_OR;
        
        if (will_jump != 0)
            this.r_ip = offset;
        else
            this.r_ip++;
    }
    public void do_inst(int opcode, int a, int b) {
        if ((b >> ARG_TYPE_SHIFT) != 0)
            b = this.registers[b & 0x3f];
        boolean is_reg_a = (a >> ARG_TYPE_SHIFT) != 0;
        if (is_reg_a)
            a = a & 0x3f;

        if (opcode == OPC_MOV)
            this.registers[a] = (byte)b;
        else if (opcode == OPC_CMP) {
			this.r_flags = 0;
            a = this.registers[a];
            if (a == b)
                this.r_flags |= FLAG_EQ;
            if (a < b)
                this.r_flags |= FLAG_LE;
            if (a > b)
                this.r_flags |= FLAG_GE;
            if (a != 0 && b != 0)
                this.r_flags |= FLAG_AND;
            if (a != 0 || b != 0)
                this.r_flags |= FLAG_OR;
        }
        else if (opcode == OPC_SUB)
            this.registers[a] = (byte)((this.registers[a] - (byte)b) & 0xf);
        else if (opcode == OPC_ADD)
            this.registers[a] = (byte)((this.registers[a] + (byte)b) & 0xf);
        else if (opcode == OPC_MUL)
            this.registers[a] = (byte)((this.registers[a] * (byte)b) & 0xf);
        else if (opcode == OPC_DIV)
            this.registers[a] = (byte)((this.registers[a] / (byte)b) & 0xf);
        else if (opcode == OPC_MOD) {
            if (b == 0)
                this.registers[a] = 0xf;
            else
                this.registers[a] = (byte)((this.registers[a] % (byte)b) & 0xf);
        }
        else if (opcode == OPC_OUT)
            this.ports[this.registers[a] % 4] = (byte)b;
        else if (opcode == OPC_IN)
            this.registers[a] = this.ports_in[4 % 4];

    }

    public void reset_ports() {
        for (int i = 0; i < this.ports.length; i++)
            this.ports[i] = 0;
    }

    public void tick() {
        if ((this.r_flags & FLAG_SLEEP) != 0) {
            this.sleep_tick--;
            if (this.sleep_tick >= 0)
                return ;
            this.r_flags &= ~FLAG_SLEEP;
        }

        if (this.r_ip >= instructions.length)
            this.r_flags |= FLAG_HALT;
        if ((this.r_flags & FLAG_HALT) != 0)
            return ;

        int count = 0;
        while (this.r_ip < instructions.length && count < INST_PER_TICK) {
            int opcode = this.instructions[this.r_ip] >> OPC_SHIFT;
            if (OPC_JMP <= opcode && opcode < OPC_SLEEP)
                this.do_jump(opcode, this.instructions[this.r_ip] & 0xFFFF);
            else if (opcode == OPC_SLEEP) {
                this.r_flags |= FLAG_SLEEP;
                this.sleep_tick = this.instructions[this.r_ip] & 0xFFFF;
                this.r_ip++;
                break;
            }
            else {
                int a = this.instructions[this.r_ip] >> ARG_A_SHIFT;
                int b = this.instructions[this.r_ip] >> ARG_B_SHIFT;
                a &= 0xFF;
                b &= 0xFF;
                this.do_inst(opcode, a, b);
                this.r_ip++;
            }
            count++;
        }
    }
}
