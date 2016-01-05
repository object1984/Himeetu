package com.himeetu.model;

import java.util.List;

/**
 * Created by zhangshuaiqi on 2016/1/5.
 */
public class ActiveUsers {

    /**
     * count: "8",
     * list: [{ "uid": "2","state": "1" }]
     */
    private int count = 0;
    private List<ActiveUsersItem> usersItems ;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ActiveUsersItem> getUsersItems() {
        return usersItems;
    }

    public void setUsersItems(List<ActiveUsersItem> usersItems) {
        this.usersItems = usersItems;
    }

    public static class ActiveUsersItem{
        private String uid = "";
        private String state = "";

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}
