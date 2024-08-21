package com.dongbi.projectDongbi.global.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{

    private int code;

    public BaseException(int code, String message) {
        super(message);
        this.code = code;
    }
}
