package fr.asqel.chou.utils;

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
    public static final int OPC_JMPIF = 11;
    public static final int OPC_JMPIF2 = 12;

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
}
