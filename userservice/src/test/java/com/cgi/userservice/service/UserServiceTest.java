package com.cgi.userservice.service;

import com.cgi.userservice.exception.UserAlreadyExistsException;
import com.cgi.userservice.exception.UserNotFoundException;
import com.cgi.userservice.model.User;
import com.cgi.userservice.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;
    private User user;
    private List<User> userList;
    private Optional optional;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        user = new User("surabhi@gmail.com", "pass1");
        optional = Optional.of(user);
    }

    @AfterEach
    public void tearDown() {
        user = null;
    }

    @Test
    void givenUserToSaveThenShouldReturnSavedUser() throws UserAlreadyExistsException {
        when(userRepository.save(any())).thenReturn(user);
        assertEquals(user, userService.saveUser(user));
        verify(userRepository, times(1)).save(any());
    }

    @Test
    void givenCredentialsThenShouldReturnCredentialsValid() throws UserNotFoundException {
        when(userRepository.findByEmailAndPassword(anyString(),anyString())).thenReturn(user);
        userService.findByIdAndPassword(user.getEmail(),user.getPassword());
        verify(userRepository , times(1)).findByEmailAndPassword(anyString(),anyString());
    }

    @Test
    void givenInValidCredentialsThenShouldThrowAnException(){
        when(userRepository.findByEmailAndPassword(anyString(),anyString())).thenReturn(null);
        assertThrows(UserNotFoundException.class,()->{
            userService.findByIdAndPassword(user.getEmail(),user.getPassword());
        });
    }
}