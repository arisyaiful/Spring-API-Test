package com.domain.api;

public class BaseResponse<T> {
    private String message;

    public BaseResponse(){
        super();
    }

    public void setBaseResponse(String message){
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
