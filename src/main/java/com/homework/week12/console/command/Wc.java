package com.homework.week12.console.command;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Wc implements Command {
    @Override
    public String execute(List<Path> paths) {
        if (paths == null || paths.isEmpty()) {
            return "Arguments cannot be null or empty";
        }
        if (paths.size() > 1) {
            return "wc: too many arguments";
        }
        Path path = paths.get(0);
        if(!Files.isDirectory(path)) {
            try {
                return String.valueOf(Files.lines(path)
                        .map(line -> line.split("\\s+"))
                        .flatMap(Arrays::stream)
                        .count());
            } catch (IOException e) {
                return "wc: cannot find '" + path.toAbsolutePath().normalize().toString() + "': No such file or directory";
            }
        }
        return "wc: word count cannot be performed on a directory";
    }
}
