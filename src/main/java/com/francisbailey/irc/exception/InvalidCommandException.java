package com.francisbailey.irc.exception;

/**
 * Created by fbailey on 16/11/16.
 */
public class InvalidCommandException extends Exception {

    public InvalidCommandException(String message) {
        super(message);
    }
}
