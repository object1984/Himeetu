package com.himeetu.network.volley;

import android.support.annotation.NonNull;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.himeetu.model.GsonResult;
import com.himeetu.network.dic.UrlPatten;
import com.himeetu.util.LogUtil;


/**
 * Requests to the API
 */
public class ApiRequests {


    public static GsonGetRequest<GsonResult> checkInvitationCode(@NonNull final String code, @NonNull final Response.Listener<GsonResult> listener, @NonNull final Response.ErrorListener errorListener) {
        final String url = String.format(UrlPatten.URL_CHECK_INVITATION_CODE, code);

        return doGet(url, listener, errorListener);
    }

    public static GsonGetRequest<GsonResult> checkUsername(@NonNull final String username, @NonNull final Response.Listener<GsonResult> listener, @NonNull final Response.ErrorListener errorListener) {
        final String url = String.format(UrlPatten.URL_CHECK_USERNAME, username);

        return doGet(url, listener, errorListener);
    }

    public static GsonGetRequest<GsonResult> checkNickname(@NonNull final String nickname, @NonNull final Response.Listener<GsonResult> listener, @NonNull final Response.ErrorListener errorListener) {
        final String url = String.format(UrlPatten.URL_CHECK_NICKNAME, nickname);

        return doGet(url, listener, errorListener);
    }

    public static GsonGetRequest<GsonResult> userLoginStep_1(@NonNull final String username,
                                                          @NonNull final String password,
                                                          @NonNull final Response.Listener<GsonResult> listener,
                                                          @NonNull final Response.ErrorListener errorListener) {
        final String url = String.format(UrlPatten.URL_USER_LOGIN_STEP_1, username, password);

        return doGet(url, listener, errorListener);
    }

    public static GsonGetRequest<GsonResult> userLoginStep_2(@NonNull final String key,
                                                             @NonNull final int uid,
                                                             @NonNull final int time,
                                                             @NonNull final int type,
                                                             @NonNull final Response.Listener<GsonResult> listener,
                                                             @NonNull final Response.ErrorListener errorListener) {
        final String url = String.format(UrlPatten.URL_USER_LOGIN_STEP_2, key, uid, time, type);

        return doGet(url, listener, errorListener);
    }

    public static GsonGetRequest<GsonResult> getSelfInfo(
                                                             @NonNull final Response.Listener<GsonResult> listener,
                                                             @NonNull final Response.ErrorListener errorListener) {
        final String url = String.format(UrlPatten.URL_USER_SELF_INFO);

        return doGet(url, listener, errorListener);
    }

    public static GsonGetRequest<GsonResult> userRegister(@NonNull final String username,
                                                          @NonNull final String password,
                                                          @NonNull final String safeCode,
                                                          @NonNull final String birthday,
                                                          @NonNull final String nickname,
                                                          @NonNull final String email,
                                                          @NonNull final int sex,
                                                          @NonNull final int countryCode,
                                                          @NonNull final Response.Listener<GsonResult> listener,
                                                                                                    @NonNull final Response.ErrorListener errorListener) {
        final String url = String.format(UrlPatten.URL_USER_REGISTER, username, password, email, safeCode, nickname, countryCode, birthday, sex);

        return doGet(url, listener, errorListener);
    }

    public static GsonGetRequest<GsonResult> userImgPath(@NonNull final String uid,
                                                          @NonNull final Response.Listener<GsonResult> listener,
                                                          @NonNull final Response.ErrorListener errorListener) {
        final String url = String.format(UrlPatten.URL_USER_IMG_PATH, uid);

        return doGet(url, listener, errorListener);
    }


    public static GsonGetRequest<GsonResult> userHeadImgPath(@NonNull final String uid,
                                                         @NonNull final Response.Listener<GsonResult> listener,
                                                         @NonNull final Response.ErrorListener errorListener) {
        final String url = String.format(UrlPatten.URL_USER_IMG_HEAD, uid);

        return doGet(url, listener, errorListener);
    }

    public static GsonGetRequest<GsonResult> getInviteCode(@NonNull final String name,
                                                             @NonNull final Response.Listener<GsonResult> listener,
                                                             @NonNull final Response.ErrorListener errorListener) {
        final String url = String.format(UrlPatten.URL_GET_INVITE_CODE, name);

        return doGet(url, listener, errorListener);
    }

    public static GsonGetRequest<GsonResult> updatePassWord(@NonNull final String name,
                                                            @NonNull final String pwd,
                                                            @NonNull final String newPwd,
                                                           @NonNull final Response.Listener<GsonResult> listener,
                                                           @NonNull final Response.ErrorListener errorListener) {
        final String url = String.format(UrlPatten.URL_UPDATE_PASSWORD, name,pwd,newPwd);

        return doGet(url, listener, errorListener);
    }


    public static GsonGetRequest<GsonResult> doGet(@NonNull final String url, @NonNull final Response.Listener<GsonResult> listener, @NonNull final Response.ErrorListener errorListener) {
        LogUtil.d("url", url);

        final Gson gson = new GsonBuilder()
                .registerTypeAdapter(GsonResult.class, new GsonResultDeserializer())
                .create();

        return new GsonGetRequest<GsonResult>
                (
                        url,
                        new TypeToken<GsonResult>() {
                        }.getType(),
                        gson,
                        listener,
                        errorListener
                );
    }
}
