package com.himeetu.network.dic;


import com.himeetu.app.Constants;

public class UrlPatten {


    private static final String URL_ACCOUNT_BASE = "http://" + Constants.WEB_SERVER_ACCOUNT_DOMAIN;
    private static final String URL_API_BASE = "http://" + Constants.WEB_SERVER_DOMAIN ;


        public static final String URL_CHECK_INVITATION_CODE= URL_ACCOUNT_BASE +"/invite/check?invite=%s";
        public static final String URL_CHECK_USERNAME= URL_ACCOUNT_BASE +"/user/check?name=%s";
        public static final String URL_CHECK_NICKNAME= URL_ACCOUNT_BASE +"/role/check?rolename=%s";
        public static final String URL_USER_REGISTER= URL_ACCOUNT_BASE +"/user/add?name=%s&password=%s&email=%s&safecode=%s&rolename=%s&nation=%d&birthday=%s&sex=%d";
        public static final String URL_USER_LOGIN_STEP_1= URL_ACCOUNT_BASE +"/user/login?name=%s&password=%s";
        public static final String URL_USER_LOGIN_STEP_2= URL_API_BASE +"/user/login?key=%s&uid=%d&time=%d&type=%d";
        public static final String URL_USER_SELF_INFO= URL_API_BASE +"/userdata/getselfdata";
        public static final String URL_USER_IMG_PATH=URL_API_BASE +"/sys/getuserimgpath?uid=%s";
        public static final String URL_USER_IMG_HEAD=URL_API_BASE+"/portrait/get?uid=%s";
        public static final String URL_GET_INVITE_CODE=URL_ACCOUNT_BASE+"/invite/get?name=%s";
        public static final String URL_UPDATE_PASSWORD=URL_ACCOUNT_BASE+"/user/updatepassword?name=%s&password=%s&newpassword=%s";
        public static final String URL_GET_SELF= URL_API_BASE+"/activity/getself?start=%s&limit=%s";
        public static final String URL_GET_FRIENES_LIST= URL_API_BASE+"/friend/getlist";
        public static final String URL_GET_FRIENES_IMG= URL_API_BASE+"/photo/getfriendsimg?start=%s&limit=%s";
        public static final String URL_GET_TOPICDETAILS = URL_API_BASE + "/talking/getusertalk?tid=%s&start=%s&limit=%s&lastid=%s";




}