package com.cgi.userservice.config;

import com.cgi.userservice.model.User;

import java.util.Map;

public interface JWTTokenGenerator {
    Map<String, String> generateToken(User user);
}
