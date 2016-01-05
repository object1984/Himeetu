package com.himeetu.model;

import java.util.List;

/**
 * Created by lanzhihong on 2016/1/5.
 */
public class FriendImgs {

    private List<FriendImg> friendImgs;

    public List<FriendImg> getFriendImgs() {
        return friendImgs;
    }

    public void setFriendImgs(List<FriendImg> friendImgs) {
        this.friendImgs = friendImgs;
    }

    public class FriendImg {

        /**
         * imgid : 15
         * img_path : bdae02aed31b8077d4362b35cf894ea5.jpg
         * name : 03.jpg
         * size : 105112
         * ctime : 2015-12-04 11:58:00
         * likenum : 1
         * istouched : 0
         * words_total : 2
         * words_list : [{}]
         */

        private String imgid;
        private String img_path;
        private String name;
        private String size;
        private String ctime;
        private String likenum;
        private int istouched;
        private String words_total;

        public void setImgid(String imgid) {
            this.imgid = imgid;
        }

        public void setImg_path(String img_path) {
            this.img_path = img_path;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public void setLikenum(String likenum) {
            this.likenum = likenum;
        }

        public void setIstouched(int istouched) {
            this.istouched = istouched;
        }

        public void setWords_total(String words_total) {
            this.words_total = words_total;
        }

        public String getImgid() {
            return imgid;
        }

        public String getImg_path() {
            return img_path;
        }

        public String getName() {
            return name;
        }

        public String getSize() {
            return size;
        }

        public String getCtime() {
            return ctime;
        }

        public String getLikenum() {
            return likenum;
        }

        public int getIstouched() {
            return istouched;
        }

        public String getWords_total() {
            return words_total;
        }
    }
}