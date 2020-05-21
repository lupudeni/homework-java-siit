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
import java.util.List;
import java.util.stream.Collectors;

public class IOExercises {

    public static void main(String[] args) throws IOException {
        getFileOrDirectoriesNamesFromGiven(Paths.get("./")).forEach(System.out::println);
        getFilesFromFolderByExtension(Paths.get("src", "main", "java", "com", "homework", "hackerrank"), "png")
                .forEach(System.out::println);

        System.out.println(checkIfFileExists(Paths.get("src", "main", "java", "com", "homework", "hackerrank")));

        System.out.println(getLastModifiedTime(new File(String.valueOf(Paths.get("src", "main", "java", "com", "homework", "hackerrank")))));
    }

    //1. Implement a method to get a list of all file/directory names from the given.
    static List<String> getFileOrDirectoriesNamesFromGiven(Path path) throws IOException {
        return Files.walk(path, 1)
                .map(Path::getFileName)
                .map(Path::toString)
                .collect(Collectors.toList());
    }


    //2. Implement a method to get specific files by extensions from a specified folder.
    static List<String> getFilesFromFolderByExtension(Path folder, String extension) throws IOException {
        return Files.walk(folder, 1)
                .map(Path::getFileName)
                .map(Path::toString)
                .filter(fileName -> fileName.endsWith(extension))
                .collect(Collectors.toList());
    }

    //3. Implement a method to check if a file or directory specified by pathname exists or not.

    static boolean checkIfFileExists(Path path) {
        return Files.exists(path);
    }

    //4. Implement a method to check if a file or directory has read and write permission.

    static boolean hasReadAndWritePermission(Path path) {
        return (Files.isReadable(path) && Files.isWritable(path));
    }

    //5. Implement a method to check if given pathname is a directory or a file.
    static String isFileOrDirectory(Path path) {
        if (Files.isDirectory(path)) {
            return "Directory";
        }
        if (Files.isRegularFile(path)) {
            return "File";
        }
        return "No match";
    }

//6. Implement a method to compare two files lexicographically.

    static int compareLexicographically(File file1, File file2) {
        return file1.getName().compareTo(file2.getName());
    }

//7. Implement a method to get last modified time of a file.

    static String getLastModifiedTime(File file) {
        return new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss")
                .format(file.lastModified());
    }

//8. Implement method to read input from java console.

    String readInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String data = reader.readLine();
        return data;
    }

//9. Implement a method to get file size in bytes, kb, mb.

    String getFileSize(File file) {
       return file.length() + " bytes" +
               "\n" + (file.length() / 1000) + " kb" +
               "\n" + (file.length() / 1000000) + " mb";
    }


//10. Implement a method to read contents from a file into byte array.
//InputStreamReader
//    byte[] readIntoByteArray(Path path) throws FileNotFoundException {
//        BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(path)));
//       return reader.lines()
//                .map(String::getBytes)
//                .reduce()

//11. Implement a method to read a file content line by line.
//    void readFileLineByLine(Path path) throws IOException {
//        Files.lines(path)
//                .map(line -> readInput())
    }

//
//12. Implement a method to read a plain text file.
//
//13. Implement a method to read a file line by line and store it into a variable.
//
//            14. Implement a method to store text file content line by line into an array.
//
//            15. Implement a method to write and read a plain text file.
//
//16. Implement a method to append text to an existing file.
//
//            17. Implement a method to read first 3 lines from a file.
//
//            18. Implement a method to find the longest word in a text file.


}
