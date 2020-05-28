package com.homework.week12.console.command;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LsTest {

    private static final Path TEST_ROOT_PATH = Paths.get("src", "test", "java", "com", "homework", "week12", "io_test_root");
    private Ls sut;

    @Before
    public void setUp() {
        sut = new Ls();
    }

    @BeforeClass
    public static void createTestingFiles() throws IOException {
        Path populatedDir = Files.createDirectory(TEST_ROOT_PATH.resolve("populated_dir"));
        Files.createFile(populatedDir.resolve("File1.txt"));  // an empty txt file
    }

    @AfterClass
    public static void cleanUp() throws IOException {
        Files.deleteIfExists(TEST_ROOT_PATH.resolve("populated_dir").resolve("File1.txt"));
        Files.deleteIfExists(TEST_ROOT_PATH.resolve("populated_dir"));
    }

    @Test
    public void given_List_Of_One_Path_When_Ls_Then_Return_Content_Of_Path() {
        //Given
        Path path = TEST_ROOT_PATH.resolve("populated_dir");

        //When
        String result = sut.execute(List.of(path));

        //Then
        assertThat(result).isEqualTo("populated_dir\nFile1.txt");
    }

    @Test
    public void given_Null_When_Ls_Then_Return_Null_Message() {
        //When
        String result = sut.execute(null);

        //Then
        assertThat(result).isEqualTo("Arguments cannot be null or empty");
    }

    @Test
    public void given_Empty_List_When_Ls_Then_Return_Empty_Message() {

        //When
        String result = sut.execute(Collections.emptyList());

        //Then
        assertThat(result).isEqualTo("Arguments cannot be null or empty");
    }

    @Test
    public void given_Unknown_Path_When_Ls_Then_Return_File_Not_Found_Message() {
        //Given
        Path path = TEST_ROOT_PATH.resolve("unknown");

        //When
        String result = sut.execute(List.of(path));

        //Then
        assertThat(result).isEqualTo("ls: cannot access '" + path.toAbsolutePath().normalize().toString() + "' : No such file or directory");
    }
}