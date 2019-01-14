package com.example.flowableactivity.exception;

import com.fasterxml.jackson.databind.ser.Serializers;

public class CustomException extends BaseBusinessException {

    public CustomException(String message,String code) {
        super(message,code);
    }
}
