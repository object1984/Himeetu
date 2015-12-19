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
}
