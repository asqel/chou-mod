package fr.asqel.chou.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Assembler {
    private HashMap<String, Integer> labels = new HashMap<>();
    private String last_scope = "_";
    private List<Integer> output = new ArrayList<>();
    public static final String[] instruction_names = {"mov", "cmp", "sub", "add", "mul", "div", "mod", "out", "in", "halt", "hlt"};
    public static final int[] instruction_args_len = {2,     2,     2,     2,     2,     2,     2,     2,     2,    0,      0   };
    public static final int[][] instruction_args_type = {
        {1, 1 | 2}, //mov: reg, reg | const
        {1, 1 | 2}, // cmp: reg, reg | const
        {1, 1 | 2}, //sub: reg, reg | const
        {1, 1 | 2}, //add: reg, reg | const
        {1, 1 | 2}, //mul: reg, reg | const
        {1, 1 | 2}, //div: reg, reg | const
        {1, 1 | 2}, //mod: reg, reg | const
        {1, 1}, // out: reg, reg
        {1, 1}, // in: reg, reg
        {}, // halt
        {}, // hlt
    };

    public String parse_normal(String[] tokens, int args_len) {
        if (args_len != tokens.length - 1)
            return "arg.invalid_number";
        if (tokens[0] == "hlt")
            tokens[0] = "halt";
        
        return "";
    }

    // !ODO add #define [A-Za-z_] ...
    public String build_line(String line) {
        int comment_idx = line.indexOf(";");
        if (comment_idx >= 0)
            line = line.substring(0, comment_idx);
        line = line.strip();
        if (line == "")
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
            return "token.unexpected";
        tokens[0] = tokens[0].toLowerCase();

        if (tokens[0] == "#define")
            return this.do_define(tokens);
        int inst_idx = -1;
        for (int i = 0; i < instruction_names.length; i++) {
            if (instruction_names[i] == tokens[0]) {
                inst_idx = i;
                break;
            }
        }
        if (inst_idx != -1)
            return this.parse_normal(tokens, instruction_args_len[inst_idx]);


        return "token.unexpected";
    }
}
