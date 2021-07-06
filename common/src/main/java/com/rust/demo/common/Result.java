package com.rust.demo.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@NoArgsConstructor
public class Result {
    private Integer code;
    private String msg;
    private Object data;

    public Result(HttpStatus httpStatus) {
        this(httpStatus, null);
    }

    public Result(HttpStatus httpStatus, Object data) {
        this.code = httpStatus.value();
        this.msg = httpStatus.getReasonPhrase();
        this.data = data;
    }
}
