package com.himeetu.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by object1984 on 16/1/6.
 */
public class Talk implements Serializable {
    @SerializedName("imgid")
    private String imgid;
    @SerializedName("img_path")
    private String imgPath;
    @SerializedName("name")
    private String name;
    @SerializedName("size")
    private String size;
    @SerializedName("ctime")
    private String ctime;
    @SerializedName("des")
    private String des;
    @SerializedName("uid")
    private String uid;
    @SerializedName("rolename")
    private String rolename;
    @SerializedName("portrait")
    private String portrait;
    @SerializedName("istouched")
    private Integer istouched;
    @SerializedName("likenum")
    private int likenum;
    @SerializedName("words_total")
    private int wordsTotal;
    @SerializedName("words_list")
    private List<Word> wordsList = new ArrayList<Word>();

    /**
     *
     * @return
     * The imgid
     */
    public String getImgid() {
        return imgid;
    }

    /**
     *
     * @param imgid
     * The imgid
     */
    public void setImgid(String imgid) {
        this.imgid = imgid;
    }

    /**
     *
     * @return
     * The imgPath
     */
    public String getImgPath() {
        return imgPath;
    }

    /**
     *
     * @param imgPath
     * The img_path
     */
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The size
     */
    public String getSize() {
        return size;
    }

    /**
     *
     * @param size
     * The size
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     *
     * @return
     * The ctime
     */
    public String getCtime() {
        return ctime;
    }

    /**
     *
     * @param ctime
     * The ctime
     */
    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    /**
     *
     * @return
     * The des
     */
    public String getDes() {
        return des;
    }

    /**
     *
     * @param des
     * The des
     */
    public void setDes(String des) {
        this.des = des;
    }

    /**
     *
     * @return
     * The uid
     */
    public String getUid() {
        return uid;
    }

    /**
     *
     * @param uid
     * The uid
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     *
     * @return
     * The rolename
     */
    public String getRolename() {
        return URLDecoder.decode(this.rolename);
    }

    /**
     *
     * @param rolename
     * The rolename
     */
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    /**
     *
     * @return
     * The portrait
     */
    public String getPortrait() {
        return portrait;
    }

    /**
     *
     * @param portrait
     * The portrait
     */
    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    /**
     *
     * @return
     * The istouched
     */
    public Integer getIstouched() {
        return istouched;
    }

    /**
     *
     * @param istouched
     * The istouched
     */
    public void setIstouched(Integer istouched) {
        this.istouched = istouched;
    }

    /**
     *
     * @return
     * The likenum
     */
    public int getLikenum() {
        return likenum;
    }

    /**
     *
     * @param likenum
     * The likenum
     */
    public void setLikenum(int likenum) {
        this.likenum = likenum;
    }

    /**
     *
     * @return
     * The wordsTotal
     */
    public int getWordsTotal() {
        return wordsTotal;
    }

    /**
     *
     * @param wordsTotal
     * The words_total
     */
    public void setWordsTotal(int wordsTotal) {
        this.wordsTotal = wordsTotal;
    }


    public List<Word> getWordsList() {
        return wordsList;
    }

    public void setWordsList(List<Word> wordsList) {
        this.wordsList = wordsList;
    }
}
