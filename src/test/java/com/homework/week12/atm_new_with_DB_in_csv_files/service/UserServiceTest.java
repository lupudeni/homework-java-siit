package com.homework.week12.atm_new_with_DB_in_csv_files.service;

import com.homework.exception.EntityNotFoundException;
import com.homework.week12.atm_new_with_DB_in_csv_files.domain.User;
import com.homework.week12.atm_new_with_DB_in_csv_files.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService sut;

    @Test
    public void given_Valid_Id_When_Get_User_By_Id_Then_Return_User() throws EntityNotFoundException {
        //Given
        String id = "1";
        User user = User.builder()
                .userId(id)
                .name("name name")
                .build();

        Mockito.when(userRepository.getUserById(id)).thenReturn(Optional.of(user));

        //When
        User result = sut.getUserById(id);

        //Then
        assertThat(result).isEqualTo(user);
    }

    @Test (expected = EntityNotFoundException.class)
    public void given_Invalid_Id_When_Get_User_By_Id_Then_Throw_Exception() throws EntityNotFoundException {
        //Given
        String id = "1";
        User user = User.builder()
                .userId(id)
                .name("name name")
                .build();

        Mockito.when(userRepository.getUserById(id)).thenReturn(Optional.empty());

        //When
        User result = sut.getUserById(id);
    }
}