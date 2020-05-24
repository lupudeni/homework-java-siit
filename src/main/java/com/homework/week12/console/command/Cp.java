package com.homework.week12.console.command;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Cp implements Command {
    @Override
    public String execute(List<Path> paths) {
        if(paths.size() == 1) {
            return "cp: missing operand ";
        }
        Path copyFrom = paths.get(0);
        Path copyTo = paths.get(1);
        try {
            Files.copy(copyFrom, copyTo);
            return "Copy successful";
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return "cp: cannot find '" + copyFrom.toAbsolutePath().normalize().toString() + "': No such file or directory";
        }

    }
}
