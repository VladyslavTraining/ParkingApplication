package com.delphi.nice.training.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long uuid) {
        super("Could not find User " + uuid);
    }
    public UserNotFoundException(String user) {
        super("Could not find User " + user);
    }

}
