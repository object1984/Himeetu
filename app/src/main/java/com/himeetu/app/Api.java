package com.himeetu.app;

import com.android.volley.Response;
import com.himeetu.network.volley.ApiRequests;

/**
 * Created by object1984 on 15/12/18.
 */
public class Api {
    public static void checkInvitationCode(String tag, String code, Response.Listener listener, Response.ErrorListener errorListener){
        MEETApplication.addRequest(ApiRequests.checkInvitationCode(code, listener, errorListener), tag);
    }

    public static void checkUsername(String tag, String username, Response.Listener listener, Response.ErrorListener errorListener){
        MEETApplication.addRequest(ApiRequests.checkUsername(username, listener, errorListener), tag);
    }

    public static void checkNickname(String tag, String nickname, Response.Listener listener, Response.ErrorListener errorListener){
        MEETApplication.addRequest(ApiRequests.checkNickname(nickname, listener, errorListener), tag);
    }

    public static void userLoginStep1(String tag, String username, String password, Response.Listener listener, Response.ErrorListener errorListener){
        MEETApplication.addRequest(ApiRequests.userLoginStep_1(username, password, listener, errorListener), tag);
    }

    public static void userLoginStep2(String tag, String key, int uid, int time, int type, Response.Listener listener, Response.ErrorListener errorListener){
        MEETApplication.addRequest(ApiRequests.userLoginStep_2(key, uid, time, type,  listener, errorListener), tag);
    }

    public static void userRegister(String tag, String username, String password, String email, String safeCode, String nickname,  String birthday, int countryCode, int sex, Response.Listener listener, Response.ErrorListener errorListener){
        MEETApplication.addRequest(ApiRequests.userRegister(username, password, safeCode, birthday, nickname, email, sex, countryCode,  listener, errorListener), tag);
    }

    public static void getSelfInfo(String tag, Response.Listener listener, Response.ErrorListener errorListener){
        MEETApplication.addRequest(ApiRequests.getSelfInfo( listener, errorListener), tag);
    }

    public static void getUserImgPath(String tag, String uid,Response.Listener listener, Response.ErrorListener errorListener){
        MEETApplication.addRequest(ApiRequests.userImgPath(uid, listener, errorListener), tag);
    }

    public static void getUserHeadImgPath(String tag, String uid,Response.Listener listener, Response.ErrorListener errorListener){
        MEETApplication.addRequest(ApiRequests.userHeadImgPath(uid, listener, errorListener), tag);
    }

    public static void getInviteCode(String tag, String name,Response.Listener listener, Response.ErrorListener errorListener){
        MEETApplication.addRequest(ApiRequests.getInviteCode(name, listener, errorListener), tag);
    }

    public static void updatePassWord(String tag, String name,String pwd,String newPwd,Response.Listener listener, Response.ErrorListener errorListener){
        MEETApplication.addRequest(ApiRequests.updatePassWord(name,pwd,newPwd, listener, errorListener), tag);
    }

    public static void getSelf(String tag, int statr,int limit,Response.Listener listener, Response.ErrorListener errorListener){
        MEETApplication.addRequest(ApiRequests.getSelf(statr,limit, listener, errorListener), tag);
    }

    public static void getFriendsList(String tag, Response.Listener listener, Response.ErrorListener errorListener){
        MEETApplication.addRequest(ApiRequests.getFriendsList( listener, errorListener), tag);
    }

    public static void getFriendsImg(String tag, int start,int limit ,Response.Listener listener, Response.ErrorListener errorListener){
        MEETApplication.addRequest(ApiRequests.getFriendsImg( start,limit,listener, errorListener), tag);
    }

    public static void getTopicDetails(String tag,int imgid, int start, int limit,
                                       Response.Listener listener, Response.ErrorListener errorListener){
        MEETApplication.addRequest(ApiRequests.getTopicDetails(imgid, start, limit, listener, errorListener),tag);
    }
    public  static void commentPicture(String tag,String imgid, String words,
                                       Response.Listener listener, Response.ErrorListener errorListener){
        MEETApplication.addRequest(ApiRequests.commentPicture(imgid,words,listener, errorListener),tag);
    }
    public static void commentNews(String tag, int tid, String words, Response.Listener listener, Response.ErrorListener errorListener){
        MEETApplication.addRequest(ApiRequests.commentNews(tid, words, listener, errorListener),tag);
    }
    public static void addFriends(String tag, String friendsId ,Response.Listener listener, Response.ErrorListener errorListener){
        MEETApplication.addRequest(ApiRequests.addFriend( friendsId,listener, errorListener), tag);
    }

    public static void delFriends(String tag, String friendsId ,Response.Listener listener, Response.ErrorListener errorListener){
        MEETApplication.addRequest(ApiRequests.delFriend( friendsId,listener, errorListener), tag);
    }

    public static void updateUserDataDetail(String tag,String nation,String sex,String birth,String phone,String email ,Response.Listener listener, Response.ErrorListener errorListener){
        MEETApplication.addRequest(ApiRequests.updateUserDataDetail( nation,sex,birth,phone,email,listener, errorListener), tag);
    }

    public static void getNum(String tag,String uid,Response.Listener listener, Response.ErrorListener errorListener){
        MEETApplication.addRequest(ApiRequests.getNum(uid,listener, errorListener), tag);
    }

    public static void getUserData(String tag,String uid,Response.Listener listener, Response.ErrorListener errorListener){
        MEETApplication.addRequest(ApiRequests.getUserData(uid,listener, errorListener), tag);
    }
    public static void getParticipateInActiveUsers(String tag, int id, int start, int limit, Response.Listener listener, Response.ErrorListener errorListener){
        MEETApplication.addRequest(ApiRequests.getParticipateInActiveUsers(id, start, limit, listener, errorListener),tag);
    }

    /**
     * 获取综合推荐列表
     * @param tag
     * @param listener
     * @param errorListener
     */
    public static void getHotRecommend(String tag, Response.Listener listener, Response.ErrorListener errorListener){
        MEETApplication.addRequest(ApiRequests.getHotRecommend(listener, errorListener), tag);
    }

    /**
     * 获取正在进行的活动列表
     * @param tag
     * @param start
     * @param limit
     * @param listener
     * @param errorListener
     */
    public static void getOnGoingActivity(String tag, int start, int limit,  Response.Listener listener, Response.ErrorListener errorListener){
        MEETApplication.addRequest(ApiRequests.getOnGoingActivity(start, limit, listener, errorListener), tag);
    }

    /**
     * 获取消息提醒
     * @param tag
     * @param start
     * @param limit
     * @param listener
     * @param errorListener
     */
    public static void getMessage(String tag, int start, int limit,  Response.Listener listener, Response.ErrorListener errorListener){
        MEETApplication.addRequest(ApiRequests.getMessage(start, limit, listener, errorListener), tag);
    }


    /**
     * 获取朋友的话题列表
     * @param tag
     * @param start
     * @param limit
     * @param listener
     * @param errorListener
     */
    public static void getFriendTalk(String tag, int start, int limit,  Response.Listener listener, Response.ErrorListener errorListener){
        MEETApplication.addRequest(ApiRequests.getFriendTalk(start, limit, listener, errorListener), tag);
    }


    /**
     * 用户参加活动
     * @param tag
     * @param activityId
     * @param listener
     * @param errorListener
     */
    public static void joinInTheActivities(String tag, String activityId,  Response.Listener listener, Response.ErrorListener errorListener){
        MEETApplication.addRequest(ApiRequests.joinInTheActivities(activityId,  listener, errorListener), tag);
    }

    /**
     * 获取广告列表
     * @param tag
     * @param listener
     * @param errorListener
     */
    public static void getAD(String tag, Response.Listener listener, Response.ErrorListener errorListener){
        MEETApplication.addRequest(ApiRequests.getAD(listener, errorListener), tag);
    }

    /**
     * 分享状态 图片
     * @param tag
     * @param activityId
     * @param listener
     * @param errorListener
     */
    public static void uploadState(String tag, int activityId, Response.Listener listener, Response.ErrorListener errorListener){
        MEETApplication.addRequest(ApiRequests.uploadState(activityId, listener, errorListener), tag);
    }

    /**
     * 获取粉丝列表
     * @param tag
     * @param uid
     * @param start
     * @param limit
     * @param listener
     * @param errorListener
     */
    public static void getFansList(String tag, String uid,int start,int limit, Response.Listener listener, Response.ErrorListener errorListener){
        MEETApplication.addRequest(ApiRequests.getFansList(uid, start,limit,listener, errorListener), tag);
    }
}
