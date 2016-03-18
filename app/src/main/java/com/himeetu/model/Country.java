package com.himeetu.model;

/**
 * Created by object1984 on 15/12/2.
 */
public class Country {
    private String id;
    private String enName;
    private String cnName;

    private String sortLetters;  //显示数据拼音的首字母
    private boolean isSelected;


    public Country(String id, String enName, String cnName) {
        this.id = id;
        this.enName = enName;
        this.cnName = cnName;
        this.sortLetters = enName.substring(0,1);
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
