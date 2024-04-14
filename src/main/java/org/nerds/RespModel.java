package org.nerds;

import java.util.ArrayList;
import java.util.List;

public class RespModel {

    private final String commandType;
    private final List<String> commands;

    public RespModel(String commandType) {
        this.commandType = commandType;
        this.commands = new ArrayList<>();
    }

    public List<String> commands() {
        return commands;
    }

    public String type() {
        return commandType;
    }

    public void addCommand(String c) {
        commands.add(c);
    }
}
