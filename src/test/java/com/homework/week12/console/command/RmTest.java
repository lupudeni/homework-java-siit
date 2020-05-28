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

public class RmTest {
    private static final Path TEST_ROOT_PATH = Paths.get("src", "test", "java", "com", "homework", "week12", "io_test_root");
    private Rm sut = new Rm();

    @BeforeClass
    public static void createTestingFiles() throws IOException {
        Files.createDirectory(TEST_ROOT_PATH.resolve("empty_dir"));
        Path populatedDir = Files.createDirectory(TEST_ROOT_PATH.resolve("populated_dir"));
        Files.createFile(populatedDir.resolve("File1.txt"));
        Files.createFile(populatedDir.resolve("File2.txt"));
    }

    @AfterClass
    public static void cleanUp() throws IOException {
        Files.deleteIfExists(TEST_ROOT_PATH.resolve("populated_dir").resolve("File1.txt"));
        Files.deleteIfExists(TEST_ROOT_PATH.resolve("populated_dir").resolve("File2.txt"));
        Files.deleteIfExists(TEST_ROOT_PATH.resolve("populated_dir"));
        Files.deleteIfExists(TEST_ROOT_PATH.resolve("empty_dir"));
    }

    @Test
    public void given_Path_When_Rm_Then_Remove_File_And_Return_Success_Message() {
        //Given
        Path path = TEST_ROOT_PATH.resolve("populated_dir").resolve("File1.txt");

        //When
        String result = sut.execute(List.of(path));

        //Then
        assertThat(result).isEqualTo("File(s) deleted successfully");
        assertThat(Files.exists(path)).isFalse();
    }

    @Test
    public void given_Empty_Directory_Path_When_Rm_Then_Remove_File_And_Return_Success_Message() {
        //Given
        Path path = TEST_ROOT_PATH.resolve("empty_dir");

        //When
        String result = sut.execute(List.of(path));

        //Then
        assertThat(result).isEqualTo("File(s) deleted successfully");
        assertThat(Files.exists(path)).isFalse();
    }

    @Test
    public void given_Not_Empty_Directory_Path_When_Rm_Then_Do_Not_Remove_File_And_Return_Not_Empty_Message() {
        //Given
        Path path = TEST_ROOT_PATH.resolve("populated_dir");

        //When
        String result = sut.execute(List.of(path));

        //Then
        assertThat(result).isEqualTo("rm: directory not empty");
        assertThat(Files.exists(path)).isTrue();
    }

    @Test
    public void given_Unknown_Path_When_Rm_Then_Return_Not_Found_Message() {
        //Given
        Path path = TEST_ROOT_PATH.resolve("unknown.txt");

        //When
        String result = sut.execute(List.of(path));

        //Then
        assertThat(result).isEqualTo("rm: cannot find '" + path.toAbsolutePath().normalize().toString() + "': No such file or directory");
    }

    @Test
    public void given_Path_When_Rm_Then_Return_Not_Found_Message() {
        //Given
        Path path = TEST_ROOT_PATH.resolve("unknown.txt");

        //When
        String result = sut.execute(List.of(path));

        //Then
        assertThat(result).isEqualTo("rm: cannot find '" + path.toAbsolutePath().normalize().toString() + "': No such file or directory");
    }

    @Test
    public void given_Empty_List_When_Rm_Then_Return_Missing_Message() {
        //When
        String result = sut.execute(Collections.emptyList());

        //Then
        assertThat(result).isEqualTo("Arguments cannot be null or empty");
    }

    @Test
    public void given_Null_When_Rm_Then_Return_Missing_Message() {
        //When
        String result = sut.execute(null);

        //Then
        assertThat(result).isEqualTo("Arguments cannot be null or empty");
    }
}