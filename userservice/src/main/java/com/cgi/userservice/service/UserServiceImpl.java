package com.cgi.userservice.service;

import com.cgi.userservice.exception.UserAlreadyExistsException;
import com.cgi.userservice.exception.UserNotFoundException;
import com.cgi.userservice.model.User;
import com.cgi.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User findByIdAndPassword(String emailId, String password) throws UserNotFoundException {
        User authUser = userRepository.findByEmailAndPassword(emailId, password);
        if(authUser == null){
            throw new UserNotFoundException("Invalid Id and Password");
        }
        else
        return authUser;
    }

    @Override
    public User saveUser(User user) throws UserAlreadyExistsException {
        Optional optional = userRepository.findById(user.getEmail());
        if(optional.isPresent()){
            throw new UserAlreadyExistsException("User Name Already Taken!");
        }
        else {
            User savedUser = userRepository.save(user);
            return savedUser;
        }
    }
}
