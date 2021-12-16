package com.cgi.favoriteservice.exceptions;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @Value(value = "${data.exception.message1}")
    private String message1;

    @Value(value = "${data.exception.message2}")
    private String message2;

    @Value(value = "${data.exception.message3}")
    private String message3;

    @ExceptionHandler(value = RestaurantAlreadyExistsException.class)
    public ResponseEntity<String> RestaurantAlreadyExistsException(RestaurantAlreadyExistsException restaurantAlreadyExistsException){
        return new ResponseEntity<>(message1, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<String> UserNoFoundException(UserNotFoundException userNotFoundException){
        return new ResponseEntity<>(message2 , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = TokenNotFound.class)
    public ResponseEntity<String> TokenNotFound(TokenNotFound tokenNotFound){
        return new ResponseEntity<>(message3 , HttpStatus.FORBIDDEN);
    }
}
