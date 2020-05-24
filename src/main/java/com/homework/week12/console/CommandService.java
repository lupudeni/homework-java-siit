package com.homework.week12.console;

import com.homework.week12.console.command.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class CommandService {
    private static final Map<String, Command> commands = new HashMap<>();
    private Path currentWorkingPath;


    static {
        commands.put("ls", new Ls());
        commands.put("cp", new Cp());
        commands.put("mv", new Mv());
        commands.put("rm", new Rm());
        commands.put("wc", new Wc());

    }

    public CommandService() {
        currentWorkingPath = Paths.get(".");
    }

    public String getCurrentWorkingPath() {
        return currentWorkingPath.toAbsolutePath().normalize().toString();
    }

    public String call(String command) {
        if (command == null || command.trim().isEmpty()) {
            return "Invalid command";
        }

        List<String> commandAndArguments = List.of(command.split("\\s+"));
        String commandName = commandAndArguments.get(0);


        if (commandName.equals("cd")) {
            return changeDirectory(commandAndArguments);
        }

        if (!commands.containsKey(commandName)) {
            return "Invalid command";
        }

        Command commandToExecute = commands.get(commandName);
        if (commandAndArguments.size() == 1) {
            return commandToExecute.execute(List.of(currentWorkingPath));
        } else {
            List<Path> paths = getPaths(commandAndArguments.subList(1, commandAndArguments.size()));
            return commandToExecute.execute(paths);
        }

    }

    private List<Path> getPaths(List<String> commandAndArguments) {
        return commandAndArguments.stream()
                .map(path -> currentWorkingPath.resolve(path).toAbsolutePath().normalize())
                .collect(Collectors.toList());
    }

    private String changeDirectory(List<String> commandAndArguments) {
        if (commandAndArguments.size() == 1) {
            currentWorkingPath = Paths.get(".");
            return "";
        } else {
            Path newPath = currentWorkingPath.resolve(commandAndArguments.get(1));
            if (Files.exists(newPath)) {
                currentWorkingPath = newPath;
                return "";
            } else {
                return "cd: " + newPath.toAbsolutePath().normalize().toString() + ": No such file or directory";
            }
        }
    }
}
