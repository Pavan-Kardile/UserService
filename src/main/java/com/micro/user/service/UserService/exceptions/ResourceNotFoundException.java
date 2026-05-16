package com.micro.user.service.UserService.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(){
        super("Resourse not found on server !!");
    }

    public ResourceNotFoundException(String msg){
        super(msg);
    }
}
