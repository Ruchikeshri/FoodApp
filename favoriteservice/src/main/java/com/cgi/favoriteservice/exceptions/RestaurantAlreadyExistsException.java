package com.cgi.favoriteservice.exceptions;

public class RestaurantAlreadyExistsException extends RuntimeException{
    private String message;

    public RestaurantAlreadyExistsException(){}
    public RestaurantAlreadyExistsException(String message){
        super();
        this.message=message;
    }
}
