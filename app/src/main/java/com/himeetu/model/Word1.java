package com.himeetu.model;

import com.google.gson.annotations.SerializedName;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by object1984 on 16/1/19.
 */
public class Word1 implements Serializable {
    @SerializedName("wid")
    private String wid;
    @SerializedName("id")
    private String id;
    @SerializedName("imgid")
    private String imgid;
    @SerializedName("words")
    private String words;
    @SerializedName("ctime")
    private String ctime;
    @SerializedName("uid")
    private String uid;
    @SerializedName("rolename")
    private String rolename;
    @SerializedName("portrait")
    private Object portrait;
    @SerializedName("second_num")

    private String secondNum;
    @SerializedName("second_list")
    private List<Word1> secondList = new ArrayList<Word1>();

    //UPDATE

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
    }

    public String getImgid() {
        return imgid;
    }

    public void setImgid(String imgid) {
        this.imgid = imgid;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Object getPortrait() {
        return portrait;
    }

    public void setPortrait(Object portrait) {
        this.portrait = portrait;
    }

    public String getSecondNum() {
        return secondNum;
    }

    public void setSecondNum(String secondNum) {
        this.secondNum = secondNum;
    }

    public List<Word1> getSecondList() {
        return secondList;
    }

    public void setSecondList(List<Word1> secondList) {
        this.secondList = secondList;
    }
}
