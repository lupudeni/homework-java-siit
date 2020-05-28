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

public class CpTest {

    private static final Path TEST_ROOT_PATH = Paths.get("src", "test", "java", "com", "homework", "week12", "io_test_root");
    private Cp sut = new Cp();

    @BeforeClass
    public static void createTestingFiles() throws IOException {
        Files.createDirectory(TEST_ROOT_PATH.resolve("empty_dir"));
        Path populatedDir = Files.createDirectory(TEST_ROOT_PATH.resolve("populated_dir"));
        Files.createFile(populatedDir.resolve("File1.txt"));  // an empty txt file
    }

    @AfterClass
    public static void cleanUp() throws IOException {
        Files.deleteIfExists(TEST_ROOT_PATH.resolve("populated_dir").resolve("File1.txt"));
        Files.deleteIfExists(TEST_ROOT_PATH.resolve("populated_dir"));
        Files.deleteIfExists(TEST_ROOT_PATH.resolve("empty_dir").resolve("File1.txt"));
        Files.deleteIfExists(TEST_ROOT_PATH.resolve("empty_dir"));
    }

    @Test
    public void given_List_Of_Two_Paths_When_Cp_Then_Copy_File_And_Return_Success_Message() {
        //Given
        Path source = TEST_ROOT_PATH.resolve("populated_dir").resolve("File1.txt");
        Path target = TEST_ROOT_PATH.resolve("empty_dir").resolve("File1.txt");

        //When
        String result = sut.execute(List.of(source, target));

        //Then
        assertThat(result).isEqualTo("Copy successful");
        assertThat(Files.exists(target)).isTrue();
        assertThat(Files.exists(source)).isTrue();

    }

    @Test
    public void given_List_Of_One_Path_When_Cp_Then_Return_Missing_Message() {
        //Given
        Path path = TEST_ROOT_PATH.resolve("populated_dir").resolve("File1.txt");

        //When
        String result = sut.execute(List.of(path));

        //Then
        assertThat(result).isEqualTo("cp: missing operand");
    }

    @Test
    public void given_List_With_Unknown_Path_When_Cp_Then_Return_Not_Found_Message() {
        //Given
        Path source = TEST_ROOT_PATH.resolve("populated_dir").resolve("File1.txt");
        Path target = TEST_ROOT_PATH.resolve("unknown").resolve("File1.txt");

        //When
        String result = sut.execute(List.of(source, target));

        //Then
        assertThat(result).isEqualTo("cp: No such file or directory");
        assertThat(Files.exists(target)).isFalse();
    }

    @Test
    public void given_Empty_List_When_Cp_Then_Return_Not_Empty_Message() {
        //When
        String result = sut.execute(Collections.emptyList());

        //Then
        assertThat(result).isEqualTo("Arguments cannot be null or empty");
    }

    @Test
    public void given_Null_When_Cp_Then_Return_Not_Null_Message() {
        //When
        String result = sut.execute(null);

        //Then
        assertThat(result).isEqualTo("Arguments cannot be null or empty");
    }
}
