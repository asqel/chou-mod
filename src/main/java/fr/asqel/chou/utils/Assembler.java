package fr.asqel.chou.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Assembler {
    private HashMap<String, Integer> labels = new HashMap<>();
    private HashMap<String, String> defines = new HashMap<>();
    private String last_scope = "_";
    public List<Integer> output = new ArrayList<>();

    public Assembler() {
        this.defines.put("halt", "hlt");
    }

    public static int atoi_check(String str, int start, int end, int min_inc, int max_exc) {
        if (end == -1)
            end = str.length();

        int num = 0;
        for (int i = start; i < end; i++) {
            char c = str.charAt(i);
            if (!('0' <= c && c <= '9'))
                return -1;
            num = num * 10 + (c - '0');
            if (!(min_inc <= num && num < max_exc))
                return -1;
        }

        if (end - start == 0)
            return -1;
        if (!(min_inc <= num && num < max_exc))
            return -1;

        return num;
    }


    public static int parse_arg(String str, int allowed) {
        if (str.length() == 0)
            return -1;


        int res = 0;
        int res_type = 0;

        if (str.charAt(0) == 'r') {
            int num = atoi_check(str, 1, -1, 0, 64);
            if (num == -1)
                return -1;

            res_type = 1;
            res = Interpreter.make_arg_reg(num);
        }
        else {
            int num = atoi_check(str, 0, -1, 0, 16);
            if (num == -1)
                return -1;

            res_type = 2;
            res = Interpreter.make_arg_int(num);
        }

        if ((res_type & allowed) != 0)
            return res;
        return -1;
    }

    public String parse_normal(String[] tokens, Interpreter.InstInfo info) {
        if (tokens.length - 1 != info.args.length)
            return "arg.invalid_number";
        int a = 0;
        int b = 0;
        if (tokens.length >= 2) {
            a = parse_arg(tokens[1], info.args[0]);
            if (a == -1)
                return "token.unexpected";
        }
        if (tokens.length >= 3) {
            b = parse_arg(tokens[2], info.args[1]);
            if (b == -1)
                return "token.unexpected";
        }
        output.add(Interpreter.make_inst(info.opcode, a, b));
        return "";
    }

    public String parse_sleep(String[] tokens) {
        if (tokens.length != 2)
            return "arg.invalid_number";

        int time = atoi_check(tokens[1], 0, -1, 0, 0xFFFF);
        if (time == -1)
            return "token.unexpected";

        output.add(Interpreter.make_sleep(time));
        return "";
    }

    public String do_define(String[] tokens) {
        if (tokens.length != 3)
            return "token.unexpected";
        this.defines.put(tokens[1], tokens[2]);
        return "";
    }
    public void evaluate_defines(String[] tokens) {
        for (int i = 0; i < tokens.length; i++) {
            String to_put = defines.get(tokens[i]);
            if (to_put != null)
                tokens[i] = to_put;
        }
    }

    public String parse_jump(String[] tokens) {
        if (tokens.length != 2)
            return "token.unexpected";
        Integer label = labels.get(tokens[1]);
        if (label == null)
            return "label.undefined";
        this.output.add(Interpreter.make_jump(label));

        return "";
    }

    public String parse_jump_cond(String[] tokens) {
        if (tokens.length != 2)
            return "token.unexpected";

        String suffix = tokens[0].substring(1);
        String[] mods = {"eq", "neq", "le", "leq", "ge", "geq", "and", "or"};
        int mod = -1;

        for (int i = 0; i < mods.length; i++) {
            if (suffix.equals(mods[i])) {
                mod = i;
                break;
            }
        }
        if (mod == -1)
            return "jmp.unknown_mod";

        Integer label = labels.get(tokens[1]);
        if (label == null)
            return "label.undefined";
        this.output.add(Interpreter.make_jump_cond(label, mod));
        
        return "";
    }

    public String build_line(String line) {
        int comment_idx = line.indexOf(";");
        if (comment_idx >= 0)
            line = line.substring(0, comment_idx);
        line = line.strip();
        if (line.isEmpty())
            return "";
        if (line.endsWith(":")) {
            line = line.substring(0, line.length() - 1);
            line = line.strip();
            if (line.startsWith("."))
                line = this.last_scope + line;
            else
                this.last_scope = line;
            if (this.labels.containsKey(line))
                return "label.redefinition";
            this.labels.put(line, this.output.size());
            return "";
        }
        String[] tokens = line.split("[\\s,]+");
        if (tokens.length == 0)
            return "";
        tokens[0] = tokens[0].toLowerCase();

        if (tokens[0].equals("#define"))
            return this.do_define(tokens);
        evaluate_defines(tokens);

        Interpreter.InstInfo normal_inst = null;
        try {
            normal_inst = Interpreter.InstInfo.valueOf(tokens[0]);
        }
        catch (IllegalArgumentException e) {
            normal_inst = null;
        }


        if (tokens[0].equals("sleep"))
            return this.parse_sleep(tokens);
        else if (normal_inst != null)
            return this.parse_normal(tokens, normal_inst);
        else if (tokens[0].equals("jmp") || tokens[0].equals("jump"))
            return this.parse_jump(tokens);
        else if (tokens[0].charAt(0) == 'j')
            return this.parse_jump_cond(tokens);


        return "token.unexpected";
    }
}
