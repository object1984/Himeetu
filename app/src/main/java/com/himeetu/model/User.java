package com.himeetu.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by object1984 on 15/12/19.
 */
public class User implements Serializable {
    private int uid;
    @SerializedName("rolename")
    private String nickname;
    @SerializedName("nation")
    private int countryCode;
    private int sex;
    private String birthday;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(int countryCode) {
        this.countryCode = countryCode;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
