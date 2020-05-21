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

        return Files.walk(path, 1)
                .map(Path::getFileName)
                .map(Path::toString)
                .collect(Collectors.toList());
    }


    //2. Implement a method to get specific files by extensions from a specified folder.

    List<String> getFilesFromFolderByExtension(Path folder, String extension) throws IOException {
        return Files.walk(folder, 1)
                .map(Path::getFileName)
                .map(Path::toString)
                .filter(fileName -> fileName.endsWith(extension))
                .collect(Collectors.toList());
    }

    //3. Implement a method to check if a file or directory specified by pathname exists or not.

    boolean checkIfFileExists(Path path) {
        return Files.exists(path);
    }

    //4. Implement a method to check if a file or directory has read and write permission.

    boolean hasReadAndWritePermission(Path path) {
        return (Files.isReadable(path) && Files.isWritable(path));
    }

    //5. Implement a method to check if given pathname is a directory or a file.
    String isFileOrDirectory(Path path) {
        if (Files.isDirectory(path)) {
            return "Directory";
        }
        if (Files.isRegularFile(path)) {
            return "File";
        }
        return "No match";
    }

    //6. Implement a method to compare two files lexicographically.

    int compareLexicographically(File file1, File file2) {
        return file1.getName().compareTo(file2.getName());
    }

    //7. Implement a method to get last modified time of a file.

    String getLastModifiedTime(File file) {
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
        long length = file.length();
        return length + " bytes" +
                "\n" + (length / 1024) + " kb" +
                "\n" + (length / (1024 * 1024)) + " mb";
    }

    //10. Implement a method to read contents from a file into byte array.

    byte[] readIntoByteArray(File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            return fileInputStream.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }


    //11. Implement a method to read a file content line by line.

    List<String> readFileLineByLine(Path path) throws IOException {
        return Files.lines(path)
                .collect(Collectors.toList());
    }

    //12. Implement a method to read a plain text file.

    String readPlainTextFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.lines()
                    .collect(Collectors.joining());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    //13. Implement a method to read a file line by line and store it into a variable.

    String readLineByLineAndStore(File file) {
        String result = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            result = reader.lines()
                    .collect(Collectors.joining());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    //14. Implement a method to store text file content line by line into an array.

    String[] readLineByLineAndStoreInArray(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.lines()
                    .collect(Collectors.toList())
                    .toArray(new String[]{});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[0];
    }

    //15. Implement a method to write and read a plain text file.

    String writeAndReadPlainTextFile(File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file));
             BufferedReader reader = new BufferedReader(new FileReader(file))) {
            writer.write("We are uncovering better ways of developing\n" +
                    "software by doing it and helping others do it.\n" +
                    "Through this work we have come to value:\n" +
                    "\n" +
                    "Individuals and interactions over processes and tools\n" +
                    "Working software over comprehensive documentation\n" +
                    "Customer collaboration over contract negotiation\n" +
                    "Responding to change over following a plan\n" +
                    "\n" +
                    "That is, while there is value in the items on\n" +
                    "the right, we value the items on the left more.");

            return reader.lines()
                    .collect(Collectors.joining());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    //16. Implement a method to append text to an existing file.

    boolean appendTextToExistingFile(File file, String text) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(text);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    //17. Implement a method to read first 3 lines from a file.

    List<String> readFirstNLines(File file, int n) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.lines()
                    .limit(n)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }


    //18. Implement a method to find the longest word in a text file.

    String findLongestWordInTextFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.lines()
                    .map(line -> line.split("\\s+"))
                    .flatMap(Arrays::stream)
                    .max(Comparator.comparingInt(String::length))
                    .orElse("");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
