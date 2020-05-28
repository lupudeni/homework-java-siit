package com.homework.week12.console.command;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class WcTest {
    private static final Path TEST_ROOT_PATH = Paths.get("src", "test", "java", "com", "homework", "week12", "io_test_root");
    private Wc sut = new Wc();

    @BeforeClass
    public static void createTestingFiles() throws IOException {
        Path populatedDir = Files.createDirectory(TEST_ROOT_PATH.resolve("populated_dir"));
        Files.createFile(populatedDir.resolve("File1.txt"));
        Files.createFile(populatedDir.resolve("File2.txt"));
        populateFile2(populatedDir);
    }
    private static void populateFile2(Path populatedDir) throws IOException {
        String textFile2 = "Hello!";
        Files.write(populatedDir.resolve("File2.txt"), textFile2.getBytes());
    }

    @AfterClass
    public static void cleanUp() throws IOException {
        Files.deleteIfExists(TEST_ROOT_PATH.resolve("populated_dir").resolve("File1.txt"));
        Files.deleteIfExists(TEST_ROOT_PATH.resolve("populated_dir").resolve("File2.txt"));
        Files.deleteIfExists(TEST_ROOT_PATH.resolve("populated_dir"));
    }

    @Test
    public void given_File_When_Wc_Then_Return_Word_Count() {
        //Given
        Path path = TEST_ROOT_PATH.resolve("populated_dir").resolve("File2.txt");

        //When
        String result = sut.execute(List.of(path));

        //Then
        assertThat(result).isEqualTo("1");
    }

    @Test
    public void given_List_Of_More_Paths_When_Wc_Then_Return_Too_Many_Args_Message() {
        //Given
        Path path1 = TEST_ROOT_PATH.resolve("populated_dir").resolve("File1.txt");
        Path path2 = TEST_ROOT_PATH.resolve("populated_dir").resolve("File2.txt");

        //When
        String result = sut.execute(List.of(path1, path2));

        //Then
        assertThat(result).isEqualTo("wc: too many arguments");
    }

    @Test
    public void given_Unknown_Path_When_Wc_Then_Return_Not_Found_Message() {
        //Given
        Path path = TEST_ROOT_PATH.resolve("populated_dir").resolve("unknown.txt");

        //When
        String result = sut.execute(List.of(path));

        //Then
        assertThat(result).isEqualTo("wc: cannot find '" + path.toAbsolutePath().normalize().toString() + "': No such file or directory");
    }

    @Test
    public void given_Empty_List_Path_When_Wc_Then_Return_Not_Empty_Message() {
        //When
        String result = sut.execute(Collections.emptyList());

        //Then
        assertThat(result).isEqualTo("Arguments cannot be null or empty");
    }

    @Test
    public void given_Null_Path_When_Wc_Then_Return_Not_Null_Message() {
        //When
        String result = sut.execute(null);

        //Then
        assertThat(result).isEqualTo("Arguments cannot be null or empty");
    }

    @Test
    public void given_Empty_File_When_Wc_Then_Return_Word_Count() {
        //Given
        Path path = TEST_ROOT_PATH.resolve("populated_dir").resolve("File1.txt");

        //When
        String result = sut.execute(List.of(path));

        //Then
        assertThat(result).isEqualTo("0");
    }

    @Test
    public void given_Dir_When_Wc_Then_Return_Dir_Message() {
        //Given
        Path path = TEST_ROOT_PATH.resolve("populated_dir");

        //When
        String result = sut.execute(List.of(path));

        //Then
        assertThat(result).isEqualTo("wc: word count cannot be performed on a directory");
    }
}