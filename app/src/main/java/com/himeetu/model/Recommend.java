package com.himeetu.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by object1984 on 15/11/28.
 */
public class Recommend implements Serializable {
    private int id;
    private int type;
    @SerializedName("dataid")
    private int dataId;
    @SerializedName("name")
    private String title;
    @SerializedName("img")
    private String imgPath;

    public Recommend(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
