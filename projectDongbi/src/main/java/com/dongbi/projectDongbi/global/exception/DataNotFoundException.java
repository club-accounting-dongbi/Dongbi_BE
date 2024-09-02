package com.dongbi.projectDongbi.global.exception;

public class DataNotFoundException extends BaseException{


    public DataNotFoundException() {
        super(404, "데이터가 삭제되었거나, 존재하지 않습니다.");
    }

    public DataNotFoundException(String message){
        super(404, message);
    }
}
