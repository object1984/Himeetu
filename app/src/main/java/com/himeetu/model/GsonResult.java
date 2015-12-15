package com.himeetu.model;

/**
 * Created by object1984 on 15/10/21.
 */
public class GsonResult<T> {
    private boolean success;
    private int code;
    private String msg;

    private GsonListResult<T> dataList;
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public GsonListResult<T> getDataList() {
        return dataList;
    }

    public void setDataList(GsonListResult<T> dataList) {
        this.dataList = dataList;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
