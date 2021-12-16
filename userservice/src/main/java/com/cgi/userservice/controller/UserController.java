package com.cgi.userservice.controller;

import com.cgi.userservice.config.JWTTokenGenerator;
import com.cgi.userservice.exception.UserAlreadyExistsException;
import com.cgi.userservice.exception.UserNotFoundException;
import com.cgi.userservice.model.User;
import com.cgi.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("/api/v1")
public class UserController {
    private UserService userService;
    private JWTTokenGenerator jwtTokenGenerator;

    @Autowired
    public UserController(UserService userService, JWTTokenGenerator jwtTokenGenerator) {
        this.userService = userService;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<?> RegisterUser(@RequestBody User user) throws UserAlreadyExistsException {
        try {
            return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>( e.getMessage() + "", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginDetail){
        try {
            String email = loginDetail.getEmail();
            String password = loginDetail.getPassword();
            if (email == null || password == null) {
                throw new UserNotFoundException("Username or Password cannot be empty");
            }
            User user = userService.findByIdAndPassword(email, password);
            if (user == null) {
                throw new UserNotFoundException("Invalid login credential, Please check username and password");
            }
            Map<String, String> map = jwtTokenGenerator.generateToken(user);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>( e.getMessage() + "", HttpStatus.CONFLICT);
        }
    }
}
