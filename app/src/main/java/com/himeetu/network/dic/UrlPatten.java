package com.himeetu.network.dic;


import com.himeetu.app.Constants;

public class UrlPatten {


    private static final String URL_API_BASE = "http://" + Constants.WEB_SERVER_DOMAIN ;


        public static final String URL_CHECK_INVITATION_CODE= URL_API_BASE +"/invite/check?invite=%s";
        public static final String URL_CHECK_USERNAME= URL_API_BASE +"/user/check?name=%s";
        public static final String URL_CHECK_NICKNAME= URL_API_BASE +"/role/check?rolename=%s";
        public static final String URL_USER_REGISTER= URL_API_BASE +"/user/add?name=%s&password=%s&email=%s&safecode=%s&rolename=%s&nation=%d&birthday=%s&sex=%d";
        public static final String URL_USER_LOGIN= URL_API_BASE +"/user/login?name=%s&password=%s";
}