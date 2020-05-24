package com.homework.week12.console.command;

import com.homework.week12.console.CommandService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Rm implements Command {
    @Override
    public String execute(List<Path> paths) {
        for (Path path : paths) {

            if (Files.isRegularFile(path)) {
                try {
                    Files.deleteIfExists(path);
                    return "File(s) deleted successfully";

                } catch (IOException e) {
                    return "rm: cannot find '" + path.toAbsolutePath().normalize().toString() + "': No such file or directory";

                }
            } else {
                return "rm: command can only delete files, not directories";
            }
        }
        return "rm: missing operand";
    }
}
