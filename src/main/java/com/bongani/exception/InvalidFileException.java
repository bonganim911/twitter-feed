package com.bongani.exception;


import org.apache.log4j.Logger;

import java.io.FileNotFoundException;

public class InvalidFileException extends FileNotFoundException {
    private static final Logger LOGGER = Logger.getLogger(InvalidFileException.class);

    public InvalidFileException(String message) {
        super(message);
        LOGGER.error("*********************************************");
        LOGGER.error(message);
        LOGGER.error("**********************************************");
    }


}
