package org.nerds;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommandExecutor {

    private static final Map<String, String> storage = new ConcurrentHashMap<>();

    public String execute(RespModel model) {

        String type = model.type();
        List<String> commands = model.commands();
        if ("get".equalsIgnoreCase(type)) {
            String key = commands.get(0);
            String value = storage.get(key);
            if (value == null) {
                return "$-1\r\n";
            }
            //$<length>\r\n<data>\r\n
            return String.format("$%d\r\n%s\r\n", value.length(), value);
        } else if ("set".equalsIgnoreCase(type)) {
            String key = commands.get(0);
            String value = commands.get(1);
            storage.put(key,value);
            return "+OK\r\n";
        } else {
            return "-Invalid Command\r\n";
        }
    }

}
