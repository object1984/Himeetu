package com.himeetu.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.net.URLDecoder;

/**
 * Created by object1984 on 16/3/8.
 */
public class Message implements Serializable {
    @SerializedName("userid")
    private String uid;
    @SerializedName("imgid")
    private String imgId;
    @SerializedName("rolename")
    private String rolename;
    @SerializedName("ctime")
    private String ctime;
    @SerializedName("type")
    private int type;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public String getRolename() {
        return URLDecoder.decode(this.rolename);
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
