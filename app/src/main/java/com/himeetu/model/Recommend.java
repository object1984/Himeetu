package com.himeetu.model;

import java.io.Serializable;

/**
 * Created by object1984 on 15/11/28.
 */
public class Recommend implements Serializable {
    private String title;
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
}
