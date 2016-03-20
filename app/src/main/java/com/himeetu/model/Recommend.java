package com.himeetu.model;

import android.annotation.TargetApi;
import android.os.Build;
import android.text.Html;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by object1984 on 15/11/28.
 */
public class Recommend implements Serializable {
    private int id;
    private int type;
    @SerializedName("dataid")
    private int dataId;
    private String name;
    @SerializedName("img")
    private String imgPath;

    public String getName() {
        return  Html.fromHtml(name).toString();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDataId() {
        return dataId;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }
}
