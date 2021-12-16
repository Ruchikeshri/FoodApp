package com.cgi.favoriteservice.exceptions;

public class TokenNotFound extends Exception{
    private String message;

    public TokenNotFound(){}
    public TokenNotFound(String message){
        super();
        this.message=message;
    }
}
