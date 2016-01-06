package com.himeetu.model;

import java.util.List;

/**
 * 话题评论
 * Created by user on 2016/1/5.
 *
 * { "count": "1", "list": [ { "id": "20", "tid": "1", "words": "ffkkkkkkkkkkkk", "uid": "2",
 * "ctime": "2015-12-27 00:12:24",  "second_num": "0", "second_list": [] }}
 */
public class TopicComments {
    private String count;
    private List<TopicCommentsItem> commentsItems;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<TopicCommentsItem> getCommentsItems() {
        return commentsItems;
    }

    public void setCommentsItems(List<TopicCommentsItem> commentsItems) {
        this.commentsItems = commentsItems;
    }
    //item entity
    public static class TopicCommentsItem{
        private int id;
        private int tid;
        private String words;
        private int uid;
        private String ctime;
        private int second_num;
        private List<String> secondList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTid() {
            return tid;
        }

        public void setTid(int tid) {
            this.tid = tid;
        }

        public String getWords() {
            return words;
        }

        public void setWords(String words) {
            this.words = words;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public int getSecond_num() {
            return second_num;
        }

        public void setSecond_num(int second_num) {
            this.second_num = second_num;
        }

        public List<String> getSecondList() {
            return secondList;
        }

        public void setSecondList(List<String> secondList) {
            this.secondList = secondList;
        }
    }
}
