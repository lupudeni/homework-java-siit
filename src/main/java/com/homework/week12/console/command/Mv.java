package com.homework.week12.console.command;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Mv implements Command {
    @Override
    public String execute(List<Path> paths) {
        if(paths.size() == 1) {
            return "mv: missing operand ";
        }

        Path source = paths.get(0);
        Path target = paths.get(1);

        try {
                Files.move(source, target);
                return "Move successful";
        } catch (IOException e) {

            return "mv: cannot find '" + source.toAbsolutePath().normalize().toString() + "': No such file or directory";
        }
    }

}
