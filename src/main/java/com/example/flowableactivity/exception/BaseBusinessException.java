package com.example.flowableactivity.exception;



public class BaseBusinessException extends RuntimeException{

    private String code;

//    // 给子类用的方法
//    public BaseBusinessException(ResponseCodeEnum responseCodeEnum) {
//        this(responseCodeEnum.getMessage(), responseCodeEnum.getCode());
//    }

    public BaseBusinessException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
