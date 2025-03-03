package com.contentfarm.file.operation.springboot.starter.pojo;

public class AsyncOperationResult<T> {
    private final boolean success;
    private final T data;
    private final Throwable failCause;

    private AsyncOperationResult(boolean success, T data, Throwable failCause) {
        this.success = success;
        this.data = data;
        this.failCause = failCause;
    }

    public static <T> AsyncOperationResult<T> ofSuccess(T data) {
        return new AsyncOperationResult<>(true, data, null);
    }

    public static <T> AsyncOperationResult<T> ofFailure(Throwable failCause) {
        return new AsyncOperationResult<>(false, null, failCause);
    }

    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public Throwable getFailCause() {
        return failCause;
    }
}
