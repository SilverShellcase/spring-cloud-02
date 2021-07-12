package com.rust.demo.util;

import com.rust.demo.common.Result;
import org.springframework.http.HttpStatus;

public class ResultUtil {

    public static Result success() {
        return new Result(HttpStatus.OK);
    }

    public static Result success(Object data) {
        return new Result(HttpStatus.OK, data);
    }

    public static Result failed() {
        return new Result(HttpStatus.BAD_REQUEST);
    }

    public static Result failed(String msg) {
        return new Result(HttpStatus.BAD_REQUEST, msg);
    }

    public static Result error() {
        return new Result(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static Result error(String msg) {
        return new Result(HttpStatus.INTERNAL_SERVER_ERROR, msg);
    }
}
