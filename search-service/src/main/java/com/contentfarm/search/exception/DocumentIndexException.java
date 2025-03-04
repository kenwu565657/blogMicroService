package com.contentfarm.search.exception;

import java.text.MessageFormat;
import java.util.List;

public class DocumentIndexException extends RuntimeException {
    private final boolean isInputProblem;

    private DocumentIndexException(String message, boolean isInputProblem) {
        super(message);
        this.isInputProblem = isInputProblem;
    }

  public static DocumentIndexException ofNullDocument() {
    return new DocumentIndexException("Can Not Index Null Document.", true);
  }

  public static DocumentIndexException ofMissingField(List<String> fields) {
      return new DocumentIndexException(MessageFormat.format("Found Missing field(s): {0}.", String.join(", ", fields)), true);
   }

    public static DocumentIndexException ofUnExpectedException(Throwable e) {
        return new DocumentIndexException(MessageFormat.format("Found UnexpectedException: {0}.", e.getMessage()), false);
    }

    public boolean isInputProblem() {
        return isInputProblem;
    }
}
