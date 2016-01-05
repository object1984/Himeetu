package com.himeetu.model.service;

import java.util.List;

/**
 * 活动实体类
 * Created by lanzhihong on 2016/1/5.
 */
public class Activitys{
    private List<Activity> list;

    public void setActivitys(List<Activity> activitys) {
        this.list = activitys;
    }

    public List<Activity> getActivitys() {
        return list;
    }

    public class Activity {

    /**
     * id : 0
     * name : hh
     * address : beijing
     * starttime : 2015-10-1010: 00: 00
     * endtime : 2015-10-1010: 00: 00
     * img : imgmd5name.png
     * des : …
     * state : 2
     */

    private int id;
    private String name;
    private String address;
    private String starttime;
    private String endtime;
    private String img;
    private String des;
    private int state;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getStarttime() {
        return starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public String getImg() {
        return img;
    }

    public String getDes() {
        return des;
    }

    public int getState() {
        return state;
    }
}
}