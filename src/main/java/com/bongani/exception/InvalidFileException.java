package com.bongani.exception;


import java.io.FileNotFoundException;

public class InvalidFileException extends FileNotFoundException {
    public InvalidFileException(String message) {
        super(message);
    }
}
