package com.himeetu.model;

import java.util.List;

/**
 * Created by lanzhihong on 2016/1/4.
 */
public class Friend {
    private List<list> list;

    public List<Friend.list> getList() {
        return list;
    }

    public void setList(List<Friend.list> list) {
        this.list = list;
    }

    public class list {
        private String friend;

        private String ctime;

        private String rolename;

        private String portrait;

        private String uid;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public void setFriend(String friend) {
            this.friend = friend;
        }

        public String getFriend() {
            return this.friend;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getCtime() {
            return this.ctime;
        }

        public void setRolename(String rolename) {
            this.rolename = rolename;
        }

        public String getRolename() {
            return this.rolename;
        }
    }


}
