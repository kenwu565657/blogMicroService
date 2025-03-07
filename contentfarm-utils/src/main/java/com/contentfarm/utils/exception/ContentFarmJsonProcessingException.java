package com.contentfarm.utils.exception;

import java.text.MessageFormat;

public class ContentFarmJsonProcessingException extends RuntimeException {
    private static final String DEFAULT_MESSAGE_TEMPLATE = "Content Farm JSON processing error. Message: {0}.";

    private ContentFarmJsonProcessingException(String message) {
        super(message);
    }

    public static ContentFarmJsonProcessingException of(String message) {
        String builtMessage = MessageFormat.format(DEFAULT_MESSAGE_TEMPLATE, message);
        return new ContentFarmJsonProcessingException(builtMessage);
    }
}
