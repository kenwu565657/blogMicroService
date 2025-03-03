package com.contentfarm.file.operation.springboot.starter.exception;

public class FileOperationException extends RuntimeException {
    private final static String FILE_NAME_NOT_EXIST_MESSAGE = "File Name Not Exist.";
    private final static String FILE_DIRECTORY_NOT_EXIST_MESSAGE = "File Directory Not Exist.";
    private final static String UNEXPECTED_ERROR = "Unexpected Error.";
    public FileOperationException(String message) {
        super(message);
    }

    public static FileOperationException ofFileNameNotExist() {
        return new FileOperationException(FILE_NAME_NOT_EXIST_MESSAGE);
    }

    public static FileOperationException ofDirectoryNotExist() {
        return new FileOperationException(FILE_DIRECTORY_NOT_EXIST_MESSAGE);
    }

    public static FileOperationException ofUnexpectedError() {
        return new FileOperationException(UNEXPECTED_ERROR);
    }
}
