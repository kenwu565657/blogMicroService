package com.contentfarm.search.exception;

public class DocumentNotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Document not found.";
    private DocumentNotFoundException(String message) {
        super(message);
    }

    public static DocumentNotFoundException of() {
        return of(DEFAULT_MESSAGE);
    }

    public static DocumentNotFoundException of(String message) {
        return new DocumentNotFoundException(message);
    }
}
