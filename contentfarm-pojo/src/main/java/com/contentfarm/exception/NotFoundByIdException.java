package com.contentfarm.exception;

import java.text.MessageFormat;

public class NotFoundByIdException extends RuntimeException {
    private NotFoundByIdException(String message) {
        super(message);
    }

    public static NotFoundByIdException of(String id) {
        return new NotFoundByIdException(MessageFormat.format("Not Found By Id: {0}.", id));
    }
}
