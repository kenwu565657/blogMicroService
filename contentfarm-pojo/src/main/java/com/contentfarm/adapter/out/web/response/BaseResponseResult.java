package com.contentfarm.adapter.out.web.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponseResult {
    private Integer code;
    private String message;
}
