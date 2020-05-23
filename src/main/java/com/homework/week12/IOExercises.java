package com.homework.week12;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import javax.swing.filechooser.FileFilter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class IOExercises {

    //1. Implement a method to get a list of all file/directory names from the given.

    List<String> getFileOrDirectoriesNamesFromGiven(Path path) throws IOException {
        if (path == null) {
            return Collections.emptyList();
        }
        return Files.walk(path, 1)
                .map(Path::getFileName)
                .map(Path::toString)
                .collect(Collectors.toList());
    }

    //2. Implement a method to get specific files by extensions from a specified folder.

    List<String> getFilesFromFolderByExtension(Path folder, String extension) throws IOException {
        if (folder == null || extension == null) {
            return Collections.emptyList();
        }
        return Files.walk(folder, 1)
                .map(Path::getFileName)
                .map(Path::toString)
                .filter(fileName -> fileName.endsWith(extension))
                .collect(Collectors.toList());
    }

    //3. Implement a method to check if a file or directory specified by pathname exists or not.

    boolean checkIfFileExists(Path path) {
        if (path == null) {
            return false;
        }
        return Files.exists(path);
    }

    //4. Implement a method to check if a file or directory has read and write permission.

    boolean hasReadAndWritePermission(Path path) {
        if (path == null) {
            return false;
        }
        return (Files.isReadable(path) && Files.isWritable(path));
    }

    //5. Implement a method to check if given pathname is a directory or a file.
    String isFileOrDirectory(Path path) {
        if (path != null) {
            if (Files.isDirectory(path)) {
                return "Directory";
            }
            if (Files.isRegularFile(path)) {
                return "File";
            }
        }
        return "No match";
    }

    //6. Implement a method to compare two files lexicographically.

    int compareLexicographically(File file1, File file2) {
        try {
            return file1.getName().compareTo(file2.getName());
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Parameters cannot be null");
        }
    }

    //7. Implement a method to get last modified time of a file.

    String getLastModifiedTime(File file) {
        if (file == null || file.lastModified() == 0L) {
            return "File does not exist";
        }
        return new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss")
                .format(file.lastModified());
    }

    //8. Implement method to read input from java console.

    String readInputFromConsole() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    //9. Implement a method to get file size in bytes, kb, mb.

    String getFileSize(File file) {
        if (file == null || !file.exists()) {
            return "File not found";
        }
        long length = file.length();
        return length + " bytes" +
                "\n" + (length / 1024) + " kb" +
                "\n" + (length / (1024 * 1024)) + " mb";
    }

    //10. Implement a method to read contents from a file into byte array.

    byte[] readIntoByteArray(File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            return fileInputStream.readAllBytes();
        } catch (IOException | NullPointerException e) {
            throw new IllegalArgumentException("Invalid or Nonexistent parameter");
        }
    }


    //11. Implement a method to read a file content line by line.

    List<String> readFileLineByLine(Path path) {
        try {
            return Files.lines(path)
                    .collect(Collectors.toList());
        } catch (IOException | NullPointerException e) {
            throw new IllegalArgumentException("Invalid or Nonexistent parameter");
        }
    }

    //12. Implement a method to read a plain text file.

    String readPlainTextFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.lines()
                    .collect(Collectors.joining());
        } catch (IOException | NullPointerException e) {
            throw new IllegalArgumentException("Invalid or Nonexistent parameter");
        }
    }

    //13. Implement a method to read a file line by line and store it into a variable.

    String readLineByLineAndStore(File file) {
        String result = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            result = reader.lines()
                    .collect(Collectors.joining());
        } catch (IOException | NullPointerException e) {
            throw new IllegalArgumentException("Invalid or Nonexistent parameter");
        }
        return result;
    }

    //14. Implement a method to store text file content line by line into an array.

    String[] readLineByLineAndStoreInArray(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.lines()
                    .collect(Collectors.toList())
                    .toArray(new String[]{});
        } catch (IOException | NullPointerException e) {
            throw new IllegalArgumentException("Invalid or Nonexistent parameter");
        }
    }

    //15. Implement a method to write and read a plain text file.

    String writeAndReadPlainTextFile(File file, String text) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file));
             BufferedReader reader = new BufferedReader(new FileReader(file))) {
            writer.write(text);
            writer.flush();
            return reader.lines()
                    .collect(Collectors.joining());

        } catch (IOException | NullPointerException e) {
            throw new IllegalArgumentException("Invalid or Nonexistent parameter");
        }
    }

    //16. Implement a method to append text to an existing file.

    String appendTextToExistingFile(File file, String text) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
             BufferedReader reader = new BufferedReader(new FileReader(file))) {
            writer.append(text);
            writer.flush();
            return reader.lines().collect(Collectors.joining());
        } catch (IOException | NullPointerException e) {
            throw new IllegalArgumentException("Invalid or Nonexistent parameter");
        }
    }

    //17. Implement a method to read first 3 lines from a file.

    List<String> readFirstNLines(File file, int n) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.lines()
                    .limit(n)
                    .collect(Collectors.toList());
        } catch (IOException | NullPointerException e) {
            throw new IllegalArgumentException("Invalid or Nonexistent parameter");
        }
    }


    //18. Implement a method to find the longest word in a text file.

    String findLongestWordInTextFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.lines()
                    .map(line -> line.split("\\s+"))
                    .flatMap(Arrays::stream)
                    .max(Comparator.comparingInt(String::length))
                    .orElse("");

        } catch (IOException | NullPointerException e) {
            throw new IllegalArgumentException("Invalid or Nonexistent parameter");
        }
    }
}
