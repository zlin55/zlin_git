package cn.zlin.demo.domain;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.Serializable;

public class ResultObj implements Serializable {
    private Boolean success;
    private String message;
    private Object data;

    public ResultObj(Boolean success,String message){
        this.success = success;
        this.message = message;
    }

    public ResultObj(Boolean success,String message,Object data){
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
