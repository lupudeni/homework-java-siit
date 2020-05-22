package com.homework.week12;

import org.assertj.core.api.Assertions;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class IOExercisesTest {
    private static final Path TEST_ROOT_PATH = Paths.get("src", "test", "java", "com", "homework", "week12", "io_test_root");
    private IOExercises sut;

    @Before
    public void setUp() {
        sut = new IOExercises();
    }

    @BeforeClass
    public static void myFiles() throws IOException {
        Files.createDirectory(TEST_ROOT_PATH.resolve("empty_dir"));
        Path populatedDir = Files.createDirectory(TEST_ROOT_PATH.resolve("populated_dir"));
        Files.createFile(populatedDir.resolve("File1.txt"));
        Files.createFile(populatedDir.resolve("File2.txt"));
        populateFile2(populatedDir);
        Files.createFile(populatedDir.resolve("File3.txt"));
        populateFile3(populatedDir);
        Files.createFile(populatedDir.resolve("File4.txt"));
    }


    private static void populateFile2(Path populatedDir) throws IOException {
        String textFile2 = "We are uncovering better ways of developing\n" +
                "software by doing it and helping others do it.\n" +
                "Through this work we have come to value:\n" +
                "\n" +
                "Individuals and interactions over processes and tools\n" +
                "Working software over comprehensive documentation\n" +
                "Customer collaboration over contract negotiation\n" +
                "Responding to change over following a plan\n" +
                "\n" +
                "That is, while there is value in the items on\n" +
                "the right, we value the items on the left more.";
        Files.write(populatedDir.resolve("File2.txt"), textFile2.getBytes());
    }

    private static void populateFile3(Path populatedDir) throws IOException {
        String textFile3 = "Hello!";
        Files.write(populatedDir.resolve("File3.txt"), textFile3.getBytes());
    }


    @AfterClass
    public static void cleanUp() throws IOException {
        Files.deleteIfExists(TEST_ROOT_PATH.resolve("empty_dir"));
        Files.deleteIfExists(TEST_ROOT_PATH.resolve("populated_dir").resolve("File1.txt"));
        Files.deleteIfExists(TEST_ROOT_PATH.resolve("populated_dir").resolve("File2.txt"));
        Files.deleteIfExists(TEST_ROOT_PATH.resolve("populated_dir").resolve("File3.txt"));
        Files.deleteIfExists(TEST_ROOT_PATH.resolve("populated_dir").resolve("File4.txt"));
        Files.deleteIfExists(TEST_ROOT_PATH.resolve("populated_dir"));

    }

    @Test
    public void given_Files_When_Getting_The_File_Names_Then_Return_List_Of_Names() throws IOException {
        //Given
        Path path = TEST_ROOT_PATH.resolve("populated_dir");

        //When
        List<String> fileNames = sut.getFileOrDirectoriesNamesFromGiven(path);

        //Then
        assertThat(fileNames).contains("populated_dir", "File1.txt");
    }

    @Test
    public void given_Empty_Dir_When_Getting_The_File_Names_Then_Return_Dir_Name() throws IOException {
        //Given
        Path path = TEST_ROOT_PATH.resolve("empty_dir");

        //When
        List<String> fileNames = sut.getFileOrDirectoriesNamesFromGiven(path);

        //Then
        assertThat(fileNames).contains("empty_dir");
    }

    @Test
    public void given_Null_When_Getting_The_File_Names_Then_Return_Empty_List() throws IOException {
        //Given
        Path path = null;

        //When
        List<String> fileNames = sut.getFileOrDirectoriesNamesFromGiven(path);

        //Then
        assertThat(fileNames).isEmpty();
    }

    @Test(expected = IOException.class)
    public void given_Unknown_File_When_Getting_The_File_Names_Then_Throw_IOException() throws IOException {
        //Given
        Path path = TEST_ROOT_PATH.resolve("unknown");

        //When
        List<String> fileNames = sut.getFileOrDirectoriesNamesFromGiven(path);
    }

    @Test
    public void given_Files_With_Matching_Extension_When_Get_File_By_Ext_Then_Return_File_List() throws IOException {
        //Given
        Path path = TEST_ROOT_PATH.resolve("populated_dir");
        String extension = ".txt";

        //When
        List<String> fileNames = sut.getFilesFromFolderByExtension(path, extension);

        //Then
        assertThat(fileNames).contains("File1.txt");
    }

    @Test
    public void given_Files_With_No_Matching_Extension_When_Get_File_By_Ext_Then_Return_Empty_List() throws IOException {
        //Given
        Path path = TEST_ROOT_PATH.resolve("populated_dir");
        String extension = ".png";

        //When
        List<String> fileNames = sut.getFilesFromFolderByExtension(path, extension);

        //Then
        assertThat(fileNames).isEmpty();
    }

    @Test
    public void given_Null_Path_Or_Extension_When_Get_File_By_Ext_Then_Return_Empty_List() throws IOException {
        //Given
        Path path = TEST_ROOT_PATH.resolve("populated_dir");
        String extension = null;

        //When
        List<String> fileNames = sut.getFilesFromFolderByExtension(path, extension);

        //Then
        assertThat(fileNames).isEmpty();
    }

    @Test(expected = IOException.class)
    public void given_Unknown_Path_When_Get_File_By_Ext_Then_Throw_IOException() throws IOException {
        //Given
        Path path = Paths.get("src", "test", "java", "com", "homework", "week12", "io_test_root", "unknown");
        String extension = ".txt";

        //When
        sut.getFilesFromFolderByExtension(path, extension);
    }

    @Test
    public void given_Existing_Path_When_Check_Existence_Then_Return_True() {
        //Given
        Path path = TEST_ROOT_PATH.resolve("populated_dir");

        //When
        boolean doesExist = sut.checkIfFileExists(path);

        //Then
        assertThat(doesExist).isTrue();
    }

    @Test
    public void given_Inexistent_Path_When_Check_Existence_Then_Return_False() {
        //Given
        Path path = TEST_ROOT_PATH.resolve("unknown");

        //When
        boolean doesExist = sut.checkIfFileExists(path);

        //Then
        assertThat(doesExist).isFalse();
    }

    @Test
    public void given_Null_When_Check_Existence_Then_Return_False() {
        //Given
        Path path = null;

        //When
        boolean doesExist = sut.checkIfFileExists(path);

        //Then
        assertThat(doesExist).isFalse();
    }

    @Test
    public void given_Read_And_Write_Permission_When_Check_Permission_Then_Return_true() {
        //Given
        Path path = TEST_ROOT_PATH.resolve("populated_dir").resolve("File1.txt");

        //When
        boolean hasPermissions = sut.hasReadAndWritePermission(path);

        //Then
        assertThat(hasPermissions).isTrue();
    }

    @Test
    public void given_Read_And_Write_Permission_When_Check_Permission_Then_Return_false() {
        //Given
        Path path = TEST_ROOT_PATH.resolve("populated_dir").resolve("File1.txt");
        File file = path.toFile();
        file.setWritable(false);
        file.setReadable(false);

        //When
        boolean hasPermissions = sut.hasReadAndWritePermission(path);

        //Then
        assertThat(hasPermissions).isFalse();

        //After
        file.setReadable(true);
        file.setWritable(true);
    }

    @Test
    public void given_Unknown_When_Check_Permission_Then_Return_False() {
        //Given
        Path path = TEST_ROOT_PATH.resolve("unknown");

        //When
        boolean hasPermissions = sut.hasReadAndWritePermission(path);

        //Then
        assertThat(hasPermissions).isFalse();
    }

    @Test
    public void given_Null_When_Check_Permission_Then_Return_False() {
        //Given
        Path path = null;

        //When
        boolean hasPermissions = sut.hasReadAndWritePermission(path);

        //Then
        assertThat(hasPermissions).isFalse();
    }

    @Test
    public void given_File_When_Check_File_Or_Directory_Then_Return_File() {
        //Given
        Path path = TEST_ROOT_PATH.resolve("populated_dir").resolve("File1.txt");

        //When
        String status = sut.isFileOrDirectory(path);

        //Then
        assertThat(status).isEqualTo("File");
    }

    @Test
    public void given_Directory_When_Check_File_Or_Directory_Then_Return_Directory() {
        //Given
        Path path = TEST_ROOT_PATH.resolve("populated_dir");

        //When
        String status = sut.isFileOrDirectory(path);

        //Then
        assertThat(status).isEqualTo("Directory");
    }

    @Test
    public void given_Unknown_When_Check_File_Or_Directory_Then_Return_No_Match() {
        //Given
        Path path = TEST_ROOT_PATH.resolve("unknown");

        //When
        String status = sut.isFileOrDirectory(path);

        //Then
        assertThat(status).isEqualTo("No match");
    }

    @Test
    public void given_Null_When_Check_File_Or_Directory_Then_Return_Directory() {
        //Given
        Path path = null;

        //When
        String status = sut.isFileOrDirectory(path);

        //Then
        assertThat(status).isEqualTo("No match");
    }

    @Test
    public void given_File1_And_File2_When_Compare_Lexicographically_Then_Return_Negative_Int() {
        //Given
        File file1 = TEST_ROOT_PATH.resolve("populated_dir").resolve("File1.txt").toFile();
        File file2 = TEST_ROOT_PATH.resolve("populated_dir").resolve("File2.txt").toFile();

        //When
        int compare = sut.compareLexicographically(file1, file2);

        //Then
        assertThat(compare).isLessThan(0);
    }

    @Test
    public void given_File2_And_File1_When_Compare_Lexicographically_Then_Return_Positive_Int() {
        //Given
        File file1 = TEST_ROOT_PATH.resolve("populated_dir").resolve("File1.txt").toFile();
        File file2 = TEST_ROOT_PATH.resolve("populated_dir").resolve("File2.txt").toFile();

        //When
        int compare = sut.compareLexicographically(file2, file1);

        //Then
        assertThat(compare).isGreaterThan(0);
    }

    @Test
    public void givenFile1_When_Compare_Lexicographically_Then_Return_0() {
        //Given
        File file1 = TEST_ROOT_PATH.resolve("populated_dir").resolve("File1.txt").toFile();

        //When
        int compare = sut.compareLexicographically(file1, file1);

        //Then
        assertThat(compare).isEqualTo(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void given_Unknown_When_Compare_Lexicographically_Then_Throw_Exception() {
        //Given
        File file1 = TEST_ROOT_PATH.resolve("populated_dir").resolve("File1.txt").toFile();
        //When
        sut.compareLexicographically(file1, null);
    }

    @Test
    public void given_File_When_Get_Last_Modified_Time_Then_Return_Time() {
        //Given
        File file = TEST_ROOT_PATH.toFile();

        //When
        String time = sut.getLastModifiedTime(file);

        //Then
        assertThat(time).isNotEqualTo("File does not exist");
    }

    @Test
    public void given_Unknown_File_When_Get_Last_Modified_Time_Then_Return_Not_Exists() {
        //Given
        File file = TEST_ROOT_PATH.resolve("unknwon").toFile();

        //When
        String time = sut.getLastModifiedTime(file);

        //Then
        assertThat(time).isEqualTo("File does not exist");
    }

    @Test
    public void given_Null_File_When_Get_Last_Modified_Time_Then_Return_Not_Exists() {
        //Given
        File file = null;

        //When
        String time = sut.getLastModifiedTime(file);

        //Then
        assertThat(time).isEqualTo("File does not exist");
    }


    @Test
    public void given_Empty_File_When_Get_File_Size_Then_Return_Size() {
        //Given
        File file = TEST_ROOT_PATH.resolve("populated_dir").resolve("File1.txt").toFile();

        //When
        String size = sut.getFileSize(file);

        //Then
        assertThat(size).isEqualTo("0 bytes\n" +
                "0 kb\n" +
                "0 mb");
    }

    @Test
    public void given_File_When_Get_File_Size_Then_Return_Size() throws IOException {
        //Given
        File file = TEST_ROOT_PATH.resolve("populated_dir").resolve("File2.txt").toFile();

        //When
        String size = sut.getFileSize(file);

        //Then
        assertThat(size).isEqualTo("423 bytes\n" +
                "0 kb\n" +
                "0 mb");
    }

    @Test
    public void given_Null_File_When_Get_File_Size_Then_Return_Not_Found() {
        //Given
        File file = null;

        //When
        String size = sut.getFileSize(file);

        //Then
        assertThat(size).isEqualTo("File not found");
    }

    @Test
    public void given_Unknown_File_When_Get_File_Size_Then_Return_Not_Found() {
        //Given
        File file = TEST_ROOT_PATH.resolve("unknown").toFile();

        //When
        String size = sut.getFileSize(file);

        //Then
        assertThat(size).isEqualTo("File not found");
    }

    @Test
    public void given_Text_File_When_Read_To_Byte_Array_Then_Return_Byte_Array() {
        //Given
        File file = TEST_ROOT_PATH.resolve("populated_dir").resolve("File3.txt").toFile();

        //When
        byte[] result = sut.readIntoByteArray(file);

        //Then
        assertThat(result).containsExactly(72, 101, 108, 108, 111, 33);
    }

    @Test
    public void given_Empty_File_When_Read_To_Byte_Array_Then_Return_Empty_Array() {
        //Given
        File file = TEST_ROOT_PATH.resolve("populated_dir").resolve("File1.txt").toFile();

        //When
        byte[] result = sut.readIntoByteArray(file);

        //Then
        assertThat(result).isEmpty();
    }

    @Test(expected = IllegalArgumentException.class)
    public void given_Null_When_Read_To_Byte_Array_Then_Throw_Exception() {
        sut.readIntoByteArray(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void given_Unknown_File_When_Read_To_Byte_Array_Then_Return_Empty_Array() {
        //Given
        File file = TEST_ROOT_PATH.resolve("populated_dir").resolve("unknown").toFile();

        //When
        byte[] result = sut.readIntoByteArray(file);
    }

    @Test
    public void given_File_When_Read_Line_By_Line_Then_Return_List_Of_String_Lines() {
        //Given
        Path path = TEST_ROOT_PATH.resolve("populated_dir").resolve("File3.txt");

        //When
        List<String> result = sut.readFileLineByLine(path);

        //Then
        assertThat(result).isEqualTo(Arrays.asList("Hello!"));
    }

    @Test
    public void given_Empty_File_When_Read_Line_By_Line_Then_Return_Empty_List() {
        //Given
        Path path = TEST_ROOT_PATH.resolve("populated_dir").resolve("File1.txt");

        //When
        List<String> result = sut.readFileLineByLine(path);

        //Then
        assertThat(result).isEmpty();
    }

    @Test(expected = IllegalArgumentException.class)
    public void given_Null_When_Read_Line_By_Line_Then_Throw_Exception() {
        sut.readFileLineByLine(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void given_Unknown_File_When_Read_Line_By_Line_Then_Return_Empty_List() {
        //Given
        Path path = TEST_ROOT_PATH.resolve("populated_dir").resolve("unknown");

        //When
        sut.readFileLineByLine(path);
    }

    @Test
    public void given_Plain_Text_File_When_Read_Then_Return_Contents() {
        //Given
        File file = TEST_ROOT_PATH.resolve("populated_dir").resolve("File3.txt").toFile();

        //When
        String result = sut.readPlainTextFile(file);

        //Then
        assertThat(result).isEqualTo("Hello!");
    }

    @Test
    public void given_Empty_Text_File_When_Read_Then_Return_Empty_String() {
        //Given
        File file = TEST_ROOT_PATH.resolve("populated_dir").resolve("File1.txt").toFile();

        //When
        String result = sut.readPlainTextFile(file);

        //Then
        assertThat(result).isEqualTo("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void given_Unknown_When_Read_Then_Throw_Exception() {
        //Given
        File file = TEST_ROOT_PATH.resolve("populated_dir").resolve("unknown").toFile();

        //When
        String result = sut.readPlainTextFile(file);
    }

    @Test(expected = IllegalArgumentException.class)
    public void given_Null_When_Read_Then_Throw_Exception() {
        String result = sut.readPlainTextFile(null);
    }

    @Test
    public void given_File_When_Read_And_Store_Then_Return_Contents_In_Variable() {
        //Given
        File file = TEST_ROOT_PATH.resolve("populated_dir").resolve("File3.txt").toFile();

        //When
        String result = sut.readLineByLineAndStore(file);

        //Then
        assertThat(result).isEqualTo("Hello!");
    }

    @Test
    public void given_Empty_File_When_Read_And_Store_Then_Return_Contents_In_Variable() {
        //Given
        File file = TEST_ROOT_PATH.resolve("populated_dir").resolve("File1.txt").toFile();

        //When
        String result = sut.readLineByLineAndStore(file);

        //Then
        assertThat(result).isEqualTo("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void given_Unknown_File_When_Read_And_Store_Then_Throw_Exception() {
        //Given
        File file = TEST_ROOT_PATH.resolve("populated_dir").resolve("unknown").toFile();

        //When
        String result = sut.readLineByLineAndStore(file);
    }

    @Test(expected = IllegalArgumentException.class)
    public void given_Null_When_Read_And_Store_Then_Throw_Exception() {
        String result = sut.readLineByLineAndStore(null);
    }

    @Test
    public void given_File_When_Read_And_Store_In_Array_Then_Return_Array_With_Contents() {
        //Given
        File file = TEST_ROOT_PATH.resolve("populated_dir").resolve("File3.txt").toFile();

        //When
        String[] result = sut.readLineByLineAndStoreInArray(file);

        //Then
        assertThat(result).containsExactly("Hello!");
    }

    @Test
    public void given_Empty_File_When_Read_And_Store_In_Array_Then_Return_Empty_Array() {
        //Given
        File file = TEST_ROOT_PATH.resolve("populated_dir").resolve("File1.txt").toFile();

        //When
        String[] result = sut.readLineByLineAndStoreInArray(file);

        //Then
        assertThat(result).isEmpty();
    }

    @Test(expected = IllegalArgumentException.class)
    public void given_Unknown_File_When_Read_And_Store_In_Array_Then_Throw_Exception() {
        //Given
        File file = TEST_ROOT_PATH.resolve("populated_dir").resolve("unknown").toFile();

        //When
        sut.readLineByLineAndStoreInArray(file);
    }

    @Test(expected = IllegalArgumentException.class)
    public void given_Null_When_Read_And_Store_In_Array_Then_Throw_Exception() {
        sut.readLineByLineAndStoreInArray(null);
    }



    @Test
    public void given_File_And_Nr_Of_Lines_When_Read_First_N_Nr_Of_Lines_Then_Return_Lines(){
        //Given
        File file = TEST_ROOT_PATH.resolve("populated_dir").resolve("File2.txt").toFile();
        int n = 3;

        //When
        List<String> result = sut.readFirstNLines(file, n);

        //Then
        assertThat(result).isEqualTo(Arrays.asList("We are uncovering better ways of developing",
                "software by doing it and helping others do it.",
                "Through this work we have come to value:"));
    }

    @Test
    public void given_Shorter_File_And_Nr_Of_Lines_When_Read_First_N_Nr_Of_Lines_Then_Return_Lines(){
        //Given
        File file = TEST_ROOT_PATH.resolve("populated_dir").resolve("File3.txt").toFile();
        int n = 3;

        //When
        List<String> result = sut.readFirstNLines(file, n);

        //Then
        assertThat(result).isEqualTo(Collections.singletonList("Hello!"));
    }

    @Test
    public void given_Empty_File_And_Nr_Of_Lines_When_Read_First_N_Nr_Of_Lines_Then_Throw_Exception(){
        //Given
        File file = TEST_ROOT_PATH.resolve("populated_dir").resolve("File3.txt").toFile();
        int n = 3;

        //When
        List<String> result = sut.readFirstNLines(file, n);

        //Then
        assertThat(result).isEqualTo(Collections.singletonList("Hello!"));
    }



    @Test
    public void given_Unknown_File_And_Nr_Of_Lines_When_Read_First_N_Nr_Of_Lines_Then_Throw_Exception(){
        //Given
        File file = TEST_ROOT_PATH.resolve("populated_dir").resolve("File3.txt").toFile();
        int n = 3;

        //When
        List<String> result = sut.readFirstNLines(file, n);

        //Then
        assertThat(result).isEqualTo(Collections.singletonList("Hello!"));
    }


}
