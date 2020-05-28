package com.homework.week12.console;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class CommandServiceTest {
    private static final Path TEST_ROOT_PATH = Paths.get("src", "test", "java", "com", "homework", "week12", "io_test_root");
    private CommandService sut;

    @Before
    public void setUp() {
        sut = new CommandService(TEST_ROOT_PATH);
    }

    @BeforeClass
    public static void createTestingFiles() throws IOException {
        Path populatedDir = Files.createDirectory(TEST_ROOT_PATH.resolve("populated_dir"));
        Files.createFile(populatedDir.resolve("File1.txt"));  // an empty txt file
        Files.createDirectory(TEST_ROOT_PATH.resolve("empty_dir"));
    }

    @AfterClass
    public static void cleanUp() throws IOException {
        Files.deleteIfExists(TEST_ROOT_PATH.resolve("populated_dir").resolve("File1.txt"));
        Files.deleteIfExists(TEST_ROOT_PATH.resolve("populated_dir"));
        Files.deleteIfExists(TEST_ROOT_PATH.resolve("empty_dir").resolve("File1.txt"));
        Files.deleteIfExists(TEST_ROOT_PATH.resolve("empty_dir"));
    }

    @Test
    public void given_Empty_String_When_Call_Then_Return_Not_Empty_Message() {
        //When
        String result = sut.call("");

        //Then
        assertThat(result).isEqualTo("Arguments cannot be null or empty");
    }

    @Test
    public void given_Null_When_Call_Then_Return_Not_Null_Message() {
        //When
        String result = sut.call(null);

        //Then
        assertThat(result).isEqualTo("Arguments cannot be null or empty");
    }

    @Test
    public void given_Ls_Command_When_Call_Then_Return_Content_Of_Path() {
        //Given
        String command = "ls";

        //When
        String result = sut.call(command);

        //Then
        assertThat(result).isEqualTo("io_test_root\n" +
                "empty_dir\n" +
                "ReadMe.txt\n" +
                "populated_dir");
    }

    @Test
    public void given_Cp_Command_When_Call_Then_Copy_File_And_Return_Success_Message() {
        //Given
        Path source = TEST_ROOT_PATH.resolve("populated_dir").resolve("File1.txt");
        Path target = TEST_ROOT_PATH.resolve("empty_dir").resolve("File1.txt");

        //When
        String result = sut.call("cp populated_dir/File1.txt empty_dir/File1.txt");

        //Then
        assertThat(result).isEqualTo("Copy successful");
        assertThat(Files.exists(target)).isTrue();
        assertThat(Files.exists(source)).isTrue();
    }

    @Test
    public void given_Cd_Command_When_Call_Then_Return_Empty_String_And_Change_Path() {
        //When
        String result = sut.call("cd populated_dir");

        //Then
        assertThat(result).isEmpty();
        assertThat(sut.getCurrentWorkingPath())
                .isEqualTo(TEST_ROOT_PATH.resolve("populated_dir")
                        .toAbsolutePath().normalize().toString());

    }

    @Test
    public void given_Unknown_Command_When_Call_Then_Return_Invalid_Message() {
        //When
        String result = sut.call("x");

        //Then
        assertThat(result).isEqualTo("Invalid command");
    }

    @Test
    public void given_Only_Cd_When_Change_Directory_Then_Change_Path_To_Current_Working_Path_Of_Running_Program() {
        //When
        String result = sut.changeDirectory(List.of("cd"));

        //Then
        assertThat(result).isEmpty();
        assertThat(sut.getCurrentWorkingPath())
                .isEqualTo(Paths.get(".").toAbsolutePath().normalize().toString());
    }

    @Test
    public void given_Unknown_Path_When_Change_Directory_Then_Return_Not_Found_Message() {
        //Given
        String newPath = TEST_ROOT_PATH.resolve("x").toAbsolutePath().toString();

        //When
        String result = sut.changeDirectory(List.of("cd", "x"));

        //Then
        assertThat(result).isEqualTo("cd: " + newPath + ": No such file or directory");
    }
}