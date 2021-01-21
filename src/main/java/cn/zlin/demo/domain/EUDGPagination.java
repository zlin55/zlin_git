package cn.zlin.demo.domain;

import java.io.Serializable;
import java.util.List;

public class EUDGPagination implements Serializable {
    private Long code;
    private String msg;
    private Long count;
    private List<?> data;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public EUDGPagination() {}

    public EUDGPagination(Long code,String msg,long count, List<?> data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }


}
