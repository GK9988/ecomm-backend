package org.garvk.user_service.exception;

public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException(){super("Invalid Username/Password");}
}
