package com.himeetu.network.dic;


import com.himeetu.app.Constants;

public class UrlPatten {
//    public static final String URL_DOMAIN_EBY = "http://www.ebaoyang.com";
    public static final String URL_DOMAIN_EBY = "http://123.56.162.135:25641";
    public static final String URL_ACTIVATE = URL_DOMAIN_EBY + "/app/activate/activate";
    public static final String URL_CHECK_UPDATE = URL_DOMAIN_EBY + "/app/download/checkUpdate/3";

//    public static final String API_DOMAIN = BuildConfig.URL_API_DOMAIN;
//    public static final String API_PORT = BuildConfig.URL_API_PORT;
//    public static final String API_COOKIE = API_DOMAIN;
//
//    public static final String URL_WEB_DOMAIN = BuildConfig.URL_DOMAIN;
//    public static final String WEB_COOKIE= BuildConfig.URL_COOKIE;
//    public static final String URL_HOME = URL_WEB_DOMAIN + "/h5/index";
//    public static final String URL_MAKECAR = URL_WEB_DOMAIN + "/h5/yangche";
//    public static final String URL_WALLET = URL_WEB_DOMAIN + "/h5/my/wallet";
//    public static final String URL_MONEY = URL_WEB_DOMAIN + "/h5/earning";
//    public static final String URL_SCAN = URL_WEB_DOMAIN + "/h5/my/bill/sys";
//    public static final String URL_LOGIN = URL_WEB_DOMAIN + "/basic/login";

    private static final String URL_API_BASE = "http://" + Constants.WEB_SERVER_DOMAIN ;

//    public static final String URL_GET_PUSHMESSAGES = URL_API_BASE +"/wallet/message?start=%d&size=%d";
//    public static final String URL_GET_BIND_DEVICE = URL_API_BASE +"/wallet/device";
//    public static final String URL_GET_MESSAGE_UNREAD_COUNT = URL_API_BASE +"/wallet/message/unreadCount";
//    public static final String URL_GET_MESSAGE_INFO = URL_API_BASE +"/wallet/message/%d";
//    public static final String URL_READ_MESSAGE = URL_API_BASE +"/wallet/message/%d";



        public static final String URL_GET_COURSE_ALL= URL_API_BASE +"/exam/course/guest/listAllCourse";
        public static final String URL_GET_QUESTION_BY_ID= URL_API_BASE +"/exam/question/guest/questionDetail?questionId=%s";
        public static final String URL_GET_COURSE_QUESTION_BY_ID= URL_API_BASE +"/exam/examination/guest/loadQeustionIndexList?courseId=%s&chapterId=%s&examType=ORDER";
}