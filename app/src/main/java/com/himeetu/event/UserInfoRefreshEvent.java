package com.himeetu.event;

import com.himeetu.model.User;

/**
 * Created by object1984 on 15/12/19.
 */
public class UserInfoRefreshEvent {
    public User user;
    public UserInfoRefreshEvent(User user) {
        this.user = user;
    }
}
