package com.himeetu.model.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.himeetu.app.Constants;
import com.himeetu.model.User;
import com.himeetu.util.ConfigUtil;

import org.json.JSONObject;

/**
 * Created by object1984 on 15/12/19.
 */
public class UserService {
    public static User save(String  userStr){
        User user =  new Gson().fromJson(userStr, User.class);
        ConfigUtil.saveStringValue(Constants.CONFIG_USER, new Gson().toJson(user));
        return user;
    }

    public static User save(User user){
        ConfigUtil.saveStringValue(Constants.CONFIG_USER, new Gson().toJson(user));
        return user;
    }

    public static User get(){
        return new Gson().fromJson(ConfigUtil.getStringValue(Constants.CONFIG_USER), User.class);
    }

    public static void saveUserImgPath(String path){

        ConfigUtil.saveStringValue(Constants.CONFIG_USER_IMG_URL,path);
    }

    public static String getUserImgPath(){
        return ConfigUtil.getStringValue(Constants.CONFIG_USER_IMG_URL);
    }

    public static void saveUserHeadPath(String path){

        ConfigUtil.saveStringValue(Constants.CONFIG_USER_Head_URL,path);
    }

    public static String getUserHeadPath(){
        return ConfigUtil.getStringValue(Constants.CONFIG_USER_Head_URL);
    }

    public static boolean isLogin(){
        return get() == null;
    }
}
