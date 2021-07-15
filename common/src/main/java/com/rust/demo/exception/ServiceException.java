package com.rust.demo.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ServiceException extends Exception {

    public ServiceException(String msg) {
        super(msg);
    }
}
