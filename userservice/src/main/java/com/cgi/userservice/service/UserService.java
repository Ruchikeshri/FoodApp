package com.cgi.userservice.service;

import com.cgi.userservice.exception.UserAlreadyExistsException;
import com.cgi.userservice.exception.UserNotFoundException;
import com.cgi.userservice.model.User;

public interface UserService {

    User findByIdAndPassword(String email, String password) throws UserNotFoundException;
    User saveUser(User user) throws UserAlreadyExistsException;
}
