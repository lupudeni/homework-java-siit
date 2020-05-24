package com.homework.week12.console.command;

import java.nio.file.Path;
import java.util.List;

public interface Command {
    String execute(List<Path> paths);
}
