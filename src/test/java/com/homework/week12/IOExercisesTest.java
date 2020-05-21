package com.homework.week12;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class IOExercisesTest {
    private IOExercises sut;

    @Before
    public void setUp() {
        sut = new IOExercises();
    }

    @Test
    public void given_Files_When_Getting_The_File_Names_Then_Return_List_Of_Names() throws IOException {
        //Given
        Path path = Paths.get("src", "test", "java", "com", "homework", "week12", "tester");

        //When
        List<String> fileNames = sut.getFileOrDirectoriesNamesFromGiven(path);

        //Then
        assertThat(fileNames).contains("File1.txt");

    }

}
