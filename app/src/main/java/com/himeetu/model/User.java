package com.himeetu.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.net.URLDecoder;

/**
 * Created by object1984 on 15/12/19.
 */
public class User implements Serializable {
    private int uid;
    private String username;
    @SerializedName("rolename")
    private String nickname;
    @SerializedName("nation")
    private int countryCode;
    private int sex;
    private String birthday;
    private String portrait;
    private String email;
    private String telphone;


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return URLDecoder.decode(this.nickname);
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPortrait() {
        return portrait;
    }

    public String getEmail() {
        return email;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }
}
