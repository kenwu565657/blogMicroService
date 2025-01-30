package com.contentfarm.dto.common;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONResult {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private Integer code;
    private String msg;
    private Object data;

    public static JSONResult build(Integer code, String msg, Object data) {
        return new JSONResult(code, msg, data);
    }

    public JSONResult() {

    }

    public JSONResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
