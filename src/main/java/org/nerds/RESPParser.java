package org.nerds;

import java.util.Arrays;
import java.util.Set;

public class RESPParser {

    private static final Set<Character> IDENTIFIER = Set.of('+', '-', ':', '$', '*', '_', '#', ',', '(', '!', '=', '%', '~', '>');

    public RespModel parse(String msg) {
        String[] rawCommands = parse$(msg);
        RespModel respModel = new RespModel(rawCommands[0]);
        for (int i = 1; i < rawCommands.length; i++) {
            respModel.addCommand(rawCommands[i]);
        }
        return respModel;
    }

    private String[] parse$(String msg) {

        String[] lines = msg.split("\\r\\n");
        System.out.println("Lines = " + Arrays.stream(lines).toList());
        String[] command = null;
        int count = 0;
        for (String line : lines) {
            if (command == null && line.startsWith("*")) {
                char l = line.charAt(1);
                System.out.println("Length of command array = " + l);
                command = new String[Integer.parseInt(String.valueOf(l))];
            } else if (!startsWithIdentifier(line.charAt(0))) {
                assert command != null;
                command[count++] = line;
            }
        }
        return command;
    }

    private boolean startsWithIdentifier(char c) {
        return IDENTIFIER.contains(c);
    }

}
