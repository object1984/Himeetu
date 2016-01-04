package com.himeetu.model;

import java.util.List;

/**
 * Created by lanzhihong on 2016/1/4.
 */
public class Friend {


    /**
     * count : 1
     * list : [{"friend":"13","ctime":"2016-01-04 15:18:31"}]
     */

    private int count;
    /**
     * friend : 13
     * ctime : 2016-01-04 15:18:31
     */

    private List<ListEntity> list;

    public void setCount(int count) {
        this.count = count;
    }

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public List<ListEntity> getList() {
        return list;
    }

    public static class ListEntity {
        private String friend;
        private String ctime;

        public void setFriend(String friend) {
            this.friend = friend;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getFriend() {
            return friend;
        }

        public String getCtime() {
            return ctime;
        }
    }
}
