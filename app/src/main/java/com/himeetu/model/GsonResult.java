package com.himeetu.model;

/**
 * Created by object1984 on 15/10/21.
 */
public class GsonResult{
    private int code;
    private String msg;
    private String jsonStr;


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

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }
}
