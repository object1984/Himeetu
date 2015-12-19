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
}