package com.homework.week12.console.command;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Rm implements Command {
    @Override
    public String execute(List<Path> paths) {

        if(paths == null || paths.isEmpty()) {
            return "Arguments cannot be null or empty";
        }
        for (Path path : paths) {
            try {
                Files.delete(path);
                return "File(s) deleted successfully";
            } catch (DirectoryNotEmptyException e) {
                return "rm: directory not empty";

            } catch (IOException e) {
                return "rm: cannot find '" + path.toAbsolutePath().normalize().toString() + "': No such file or directory";
            }
        }
        return "rm: invalid syntax";
    }
}
