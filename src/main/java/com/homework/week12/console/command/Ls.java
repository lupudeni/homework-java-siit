package com.homework.week12.console.command;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Ls implements Command {
    @Override
    public String execute(List<Path> paths) {
        Path path = paths.get(0);
        try {
            return Files.walk(path, 1)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.joining("\n"));
        } catch (IOException e) {
            return "ls: cannot access '" + path.toAbsolutePath().normalize().toString() + "' : No such file or directory";
        }
    }
}
