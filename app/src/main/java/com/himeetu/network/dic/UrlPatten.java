package com.himeetu.network.dic;


import com.himeetu.app.Constants;

public class UrlPatten {


    private static final String URL_ACCOUNT_BASE = "http://" + Constants.WEB_SERVER_ACCOUNT_DOMAIN;
    private static final String URL_API_BASE = "http://" + Constants.WEB_SERVER_DOMAIN ;


        public static final String URL_CHECK_INVITATION_CODE= URL_ACCOUNT_BASE +"/invite/check?invite=%s";
        public static final String URL_CHECK_USERNAME= URL_ACCOUNT_BASE +"/user/check?name=%s";
        public static final String URL_CHECK_NICKNAME= URL_ACCOUNT_BASE +"/role/check?rolename=%s";
        public static final String URL_USER_REGISTER= URL_ACCOUNT_BASE +"/user/add?name=%s&password=%s&email=%s&safecode=%s&rolename=%s&nation=%s&birthday=%s&sex=%d";
        public static final String URL_USER_LOGIN_STEP_1= URL_ACCOUNT_BASE +"/user/login?name=%s&password=%s";
        public static final String URL_USER_LOGIN_STEP_2= URL_API_BASE +"/user/login?key=%s&uid=%d&time=%d&type=%d";
        public static final String URL_USER_SELF_INFO= URL_API_BASE +"/userdata/getselfdata";
        public static final String URL_USER_IMG_PATH=URL_API_BASE +"/sys/getuserimgpath?uid=%s";
        public static final String URL_USER_IMG_HEAD=URL_API_BASE+"/portrait/get?uid=%s";
        public static final String URL_GET_INVITE_CODE=URL_ACCOUNT_BASE+"/invite/get?name=%s";
        public static final String URL_UPDATE_PASSWORD=URL_ACCOUNT_BASE+"/user/updatepassword?name=%s&password=%s&newpassword=%s";
        public static final String URL_GET_SELF= URL_API_BASE+"/activity/getself?start=%s&limit=%s";
        public static final String URL_GET_FRIENES_LIST= URL_API_BASE+"/friend/getlist?start=%s&limit=%s&uid=%s";
        public static final String URL_GET_FRIENES_IMG= URL_API_BASE+"/photo/getfriendsimg?start=%s&limit=%s";
        public static final String URL_ADD_FRIENDS= URL_API_BASE+"/friend/add?uid=%s";
        public static final String URL_DEL_FRIENDS= URL_API_BASE+"/friend/del?uid=%s";
        public static final String URL_GET_TOPICDETAILS = URL_API_BASE + "/photo/gettalklist?imgid=%s&start=%s&limit=%s";
        public static final String URL_GET_USER_COMMENT = URL_API_BASE + "/talking/usertalk?tid=%s&words=%s";
        public static final String URL_USER_DATA_DETAIL = URL_API_BASE + "/userdata/update?nation=%s&sex=%s&birthday=%s&telphone=%s&email=%s";
        public static final String URL_GET_NUM = URL_API_BASE + "/friend/getnum?uid=%s";
        public static final String URL_GET_USER_DATA = URL_API_BASE + "/userdata/getuserdata?uid=%s";
        public static final String URL_GET_ACTIVE_USERS = URL_API_BASE + "/activity/getuserlist?id=%s&start=%s&limit=%s";
        //评论用户的图片
        public static final String URL_COMMENT_USERS_PICTURE = URL_API_BASE +"/photo/talk?imgid=%s&words=%s";


        //获取综合推荐列表
        public static final String URL_GET_HOT_RECOMMEND = URL_API_BASE + "/systop/get";
        //获取全部的活动列表
        public static final String URL_GET_ACTIVITY_ALL = URL_API_BASE + "/activity/getlist?start=%d&limit=％d&state=0";
        //获取还未开始的活动列表
        public static final String URL_GET_ACTIVITY_NOT_START = URL_API_BASE + "/activity/getlist?start=%d&limit=％d&state=1";
        //获取进行中的活动列表
        public static final String URL_GET_ACTIVITY_ON_GOING = URL_API_BASE + "/activity/getlist?start=%d&limit=%d&state=2";
        //获取活动中和等待中的活动列表
        public static final String URL_GET_ACTIVITY_NOT_END = URL_API_BASE + "/activity/getlist?start=%d&limit=％d&state=3";
        //获取已经结束的活动列表
        public static final String URL_GET_ACTIVITY_END = URL_API_BASE + "/activity/getlist?start=%d&limit=％d&state=4";

        //获取消息提醒
        public static final String URL_GET_MESSAGE = URL_API_BASE + "/notice/get?start=%d&limit=%d";

        //获取朋友的话题列表
       public static final String URL_GET_FRIEND_TALK = URL_API_BASE + "/photo/getfriendsimg?start=%d&limit=%d";

        //用户参加活动
       public static final String JOIN_IN_THE_ACTIVITIES = URL_API_BASE + "/activity/applyadd?id=%s";

    //获取广告列表
    public static final String URL_GET_AD = URL_API_BASE + "/advertisement/getlist";

    //上传图片
    public static final String URL_UPLOAD_STATE = URL_API_BASE + "/photo/upload";

    //获取粉丝列表
    public static final String URL_GET_FANSLIST = URL_API_BASE + "/friend/getfanslist?uid=%s&start=%s&limit=%s";

    public static final String UPLOAD_PIC = URL_API_BASE + "/portrait/set?img=%s";

    //对图片点赞
    public static final String URL_IMG_ZAN = URL_API_BASE + "/photo/setlike?imgid=%s&type=%d";

    //查询用户
    public static final String URL_SEARCH_USER = URL_API_BASE + "/userdata/getlist?rolename=%s&start=%d&limit=%d";

    //查询活动
    public static final String URL_SEARCH_ACTIVITY = URL_API_BASE + "/activity/search?searchname=%s&start=%d&limit=%d";

    //查询动态
    public static final String URL_SEARCH_TALK = URL_API_BASE + "/photo/search?des=%s&start=%d&limit=%d";

    //获取话题列表
    public static final String URL_GET_TALK_LIST = URL_API_BASE + "/talking/getlist?start=%d&limit=%d";
}