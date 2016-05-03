package com.bongani.exception;

import org.apache.log4j.Logger;

public class InvalidSyntaxException extends RuntimeException {
    private static final Logger LOGGER = Logger.getLogger(InvalidSyntaxException.class);

    public InvalidSyntaxException(String message) {
        super(message);
        LOGGER.error("*********************************************");
        LOGGER.error(message);
        LOGGER.error("**********************************************");
    }
}
