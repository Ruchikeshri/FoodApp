package com.cgi.userservice.repository;

import com.cgi.userservice.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    private User user;

    @BeforeEach
    public void setUp() {
        user =  new User("surabhi@gmail.com", "pass1");
    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
        user = null;
    }

    @Test
    public void givenUserThenShouldSaveThatUser() {
        userRepository.save(user);
        User fetchedUser = userRepository.findById(user.getEmail()).get();
        assertEquals("surabhi@gmail.com", fetchedUser.getEmail());
    }
    @Test
    public void givenUserThenShouldNotSaveThatUser() {
        User user = new User("abc@xyz.com", "password");
        assertNotSame(user, userRepository.save(user));
    }
}