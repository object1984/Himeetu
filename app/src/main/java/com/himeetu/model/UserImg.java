package com.himeetu.model;


import java.util.List;

/**
 * Created by lanzhihongPC on 15/12/19.
 */
public class UserImg {

    private int count;

    private List list;

    public void setCount(int count) {
        this.count = count;
    }

    public void setPaths(List paths) {
        list = paths;
    }

    public int getCount() {
        return count;
    }

    public List getPaths() {
        return list;
    }

    public class List{

        private String path;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            path = path;
        }
    }

}
