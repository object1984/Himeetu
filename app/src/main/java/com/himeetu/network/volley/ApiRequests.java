package com.himeetu.network.volley;

import android.os.Environment;
import android.support.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.himeetu.model.GsonResult;
import com.himeetu.network.dic.UrlPatten;
import com.himeetu.util.FileUtil;
import com.himeetu.util.LogUtil;

import java.io.File;


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

    public static GsonGetRequest<GsonResult> userLoginStep_2(@NonNull   final String key,
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

    public static GsonGetRequest<GsonResult> getSelf(@NonNull final int start,
                                                            @NonNull final int limit,
                                                           @NonNull final Response.Listener<GsonResult> listener,
                                                           @NonNull final Response.ErrorListener errorListener) {
        final String url = String.format(UrlPatten.URL_GET_SELF, start,limit);

        return doGet(url, listener, errorListener);
    }
    public static GsonGetRequest<GsonResult> getTopicDetails(@NonNull int imgid,@NonNull int start, @NonNull int limit,
                                                            @NonNull Response.Listener<GsonResult> listener,
                                                             @NonNull  Response.ErrorListener errorListener){
        final String url = String.format(UrlPatten.URL_GET_TOPICDETAILS, imgid, start, limit);
        return doGet(url, listener, errorListener);
    }

    public static GsonGetRequest<GsonResult> getFriendsList(
                                                           @NonNull final Response.Listener<GsonResult> listener,
                                                           @NonNull final Response.ErrorListener errorListener) {
        final String url = String.format(UrlPatten.URL_GET_FRIENES_LIST);

        return doGet(url, listener, errorListener);
    }

    public static GsonGetRequest<GsonResult> getFriendsImg(@NonNull final int start,
                                                           @NonNull final int limit,
                                                           @NonNull final Response.Listener<GsonResult> listener,
                                                           @NonNull final Response.ErrorListener errorListener) {
        final String url = String.format(UrlPatten.URL_GET_FRIENES_IMG,start,limit);

        return doGet(url, listener, errorListener);
    }
    public static GsonGetRequest<GsonResult> commentPicture(@NonNull String imgid,
                                                            @NonNull String words,
                                                            @NonNull final Response.Listener<GsonResult> listener,
                                                            @NonNull final Response.ErrorListener errorListener){
        final String url = String.format(UrlPatten.URL_COMMENT_USERS_PICTURE,imgid, words);

        return doGet(url, listener, errorListener);
    }
    public static GsonGetRequest<GsonResult> commentNews(@NonNull int tid,
                                                         @NonNull String words,
                                                         @NonNull final Response.Listener<GsonResult> listener,
                                                         @NonNull final Response.ErrorListener errorListener){
        final String url = String.format(UrlPatten.URL_GET_USER_COMMENT,tid, words);

        return doGet(url, listener, errorListener);
    }

    public static GsonGetRequest<GsonResult> addFriend(@NonNull final String friendId,
                                                           @NonNull final Response.Listener<GsonResult> listener,
                                                           @NonNull final Response.ErrorListener errorListener) {
        final String url = String.format(UrlPatten.URL_ADD_FRIENDS,friendId);

        return doGet(url, listener, errorListener);
    }

    public static GsonGetRequest<GsonResult> delFriend(@NonNull final String friendId,
                                                           @NonNull final Response.Listener<GsonResult> listener,
                                                           @NonNull final Response.ErrorListener errorListener) {
        final String url = String.format(UrlPatten.URL_DEL_FRIENDS,friendId);

        return doGet(url, listener, errorListener);
    }

    public static Request<?> updateUserDataDetail(@NonNull final String nation,
                                                  @NonNull final String sex,
                                                  @NonNull final String birth,
                                                  @NonNull final String phone,
                                                  @NonNull final String email,
                                                  @NonNull final Response.Listener listener,
                                                  @NonNull final Response.ErrorListener errorListener) {
        final String url = String.format(UrlPatten.URL_USER_DATA_DETAIL,nation,sex,birth,phone,email);

        return doGet(url, listener, errorListener);

    }

    public static Request<?> getNum( @NonNull final String uid,
                                     @NonNull final Response.Listener listener,
                                     @NonNull final Response.ErrorListener errorListener) {

        final String url = String.format(UrlPatten.URL_GET_NUM,uid);

        return doGet(url, listener, errorListener);
    }

    public static Request<?> getUserData( @NonNull final String uid,
                                          @NonNull final Response.Listener listener,
                                          @NonNull final Response.ErrorListener errorListener) {

        final String url = String.format(UrlPatten.URL_GET_USER_DATA,uid);

        return doGet(url, listener, errorListener);
    }
    public static Request<?> getParticipateInActiveUsers(@NonNull final int id,
                                                         @NonNull final int start,
                                                         @NonNull final int limit,
                                                         @NonNull final Response.Listener listener,
                                                         @NonNull final Response.ErrorListener errorListener){
        final String url = String.format(UrlPatten.URL_GET_ACTIVE_USERS, id, start, limit);
        return doGet(url, listener, errorListener);
    }

    public static Request<?> getOnGoingActivity( @NonNull final int start,
                                                 @NonNull final int limit,
                                          @NonNull final Response.Listener listener,
                                          @NonNull final Response.ErrorListener errorListener) {

        final String url = String.format(UrlPatten.URL_GET_ACTIVITY_ON_GOING ,start, limit);

        return doGet(url, listener, errorListener);
    }


    /**
     * 获取朋友的话题列表
     * @param start
     * @param limit
     * @param listener
     * @param errorListener
     * @return
     */
    public static Request<?> getFriendTalk(
                                                         @NonNull final int start,
                                                         @NonNull final int limit,
                                                         @NonNull final Response.Listener listener,
                                                         @NonNull final Response.ErrorListener errorListener){
        final String url = String.format(UrlPatten.URL_GET_FRIEND_TALK, start, limit);
        return doGet(url, listener, errorListener);
    }

    /**
     * 获取综合推荐列表
     * @param listener
     * @param errorListener
     * @return
     */
    public static Request<?> getHotRecommend(
                                          @NonNull final Response.Listener listener,
                                          @NonNull final Response.ErrorListener errorListener) {

        final String url = String.format(UrlPatten.URL_GET_HOT_RECOMMEND);

        return doGet(url, listener, errorListener);
    }

    /**
     * 获取广告列表
     * @param listener
     * @param errorListener
     * @return
     */
    public static Request<?> getAD(
            @NonNull final Response.Listener listener,
            @NonNull final Response.ErrorListener errorListener) {

        final String url = String.format(UrlPatten.URL_GET_AD);

        return doGet(url, listener, errorListener);
    }

    /**
     * 获取消息提醒
     * @param listener
     * @param errorListener
     * @return
     */
    public static Request<?> getMessage(
            @NonNull final int start,
            @NonNull final int limit,
            @NonNull final Response.Listener listener,
            @NonNull final Response.ErrorListener errorListener) {

        final String url = String.format(UrlPatten.URL_GET_MESSAGE, start, limit);

        return doGet(url, listener, errorListener);
    }

    /**
     * 用户参加活动
     * @param activityId
     * @param listener
     * @param errorListener
     * @return
     */
    public static Request<?> joinInTheActivities(String activityId, Response.Listener listener, Response.ErrorListener errorListener) {
        final String url = String.format(UrlPatten.JOIN_IN_THE_ACTIVITIES, activityId);
        return doGet(url, listener, errorListener);
    }

    /**
     * 获取粉丝列表
     * @param uid
     * @param start
     * @param limit
     * @param listener
     * @param errorListener
     * @return
     */
    public static Request<?> getFansList(String uid, int start ,int limit ,Response.Listener listener, Response.ErrorListener errorListener) {
        final String url = String.format(UrlPatten.URL_GET_FANSLIST,uid,start,limit );
        return doGet(url, listener, errorListener);
    }

    /**
     * 上传用户头像
     * @param pic
     * @param listener
     * @param errorListener
     * @return
     */
    public static Request<?> uploadPic(String pic ,Response.Listener listener, Response.ErrorListener errorListener) {
        final String url = String.format(UrlPatten.UPLOAD_PIC,pic);
        return doGet(url, listener, errorListener);
    }


    /***
     * 对图片点赞
     * @param imgId
     * @param type
     * @param listener
     * @param errorListener
     * @return
     */
    public static Request<?> setImgZAN(String imgId, int type, Response.Listener listener, Response.ErrorListener errorListener) {
        String url = String.format(UrlPatten.URL_IMG_ZAN, imgId, type);
        return doGet(url, listener, errorListener);
    }


    /**
     * 上传图片 分享状态
     * @param activityId
     * @param listener
     * @param errorListener
     * @return
     */
    public static Request<?> uploadState(int activityId, Response.Listener listener, Response.ErrorListener errorListener) {
        final String url = String.format(UrlPatten.URL_UPLOAD_STATE, activityId);

        File fileFolder = new File(Environment.getExternalStorageDirectory()
                + "/himeetu/temp");
        if (!fileFolder.exists()) {
            fileFolder.mkdir();
        }
        File jpgFile = new File(fileFolder, "1.jpg");
        return uploadFile(url, "file", jpgFile, listener, errorListener);
    }


    /**
     * 查询用户
     * @param user
     * @param start
     * @param limit
     * @param listener
     * @param errorListener
     * @return
     */
    public static Request<?> searchUser(
            @NonNull final String user,
            @NonNull final int start,
            @NonNull final int limit,
            @NonNull final Response.Listener listener,
            @NonNull final Response.ErrorListener errorListener){
        final String url = String.format(UrlPatten.URL_SEARCH_USER, user, start, limit);
        return doGet(url, listener, errorListener);
    }

    /**
     * 查询活动
     * @param activity
     * @param start
     * @param limit
     * @param listener
     * @param errorListener
     * @return
     */
    public static Request<?> searchActivity(
            @NonNull final String activity,
            @NonNull final int start,
            @NonNull final int limit,
            @NonNull final Response.Listener listener,
            @NonNull final Response.ErrorListener errorListener){
        final String url = String.format(UrlPatten.URL_SEARCH_ACTIVITY, activity, start, limit);
        return doGet(url, listener, errorListener);
    }

    /**
     * 查询动态
     * @param talk
     * @param start
     * @param limit
     * @param listener
     * @param errorListener
     * @return
     */
    public static Request<?> searchTalk(
            @NonNull final String talk,
            @NonNull final int start,
            @NonNull final int limit,
            @NonNull final Response.Listener listener,
            @NonNull final Response.ErrorListener errorListener){
        final String url = String.format(UrlPatten.URL_SEARCH_TALK, talk, start, limit);
        return doGet(url, listener, errorListener);
    }


    public static GsonGetRequest<GsonResult> doGet(@NonNull final String url, @NonNull final Response.Listener<GsonResult> listener, @NonNull final Response.ErrorListener errorListener) {


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

//    public static MultiPartGsonRequest<GsonResult> uploadFile(@NonNull final String url, @NonNull final Response.Listener<GsonResult> listener, @NonNull final Response.ErrorListener errorListener) {
//
//
//        final Gson gson = new GsonBuilder()
//                .registerTypeAdapter(GsonResult.class, new GsonResultDeserializer())
//                .create();
//
//        return new MultiPartGsonRequest<GsonResult>
//                (
//                        url,
//                        new TypeToken<GsonResult>() {
//                        }.getType(),
//                        gson,
//                        listener,
//                        errorListener
//                );
//
//    }

    public static MultipartRequest<GsonResult> uploadFile(@NonNull final String url, String stringPart, File file, @NonNull final Response.Listener<GsonResult> listener, @NonNull final Response.ErrorListener errorListener) {


        final Gson gson = new GsonBuilder()
                .registerTypeAdapter(GsonResult.class, new GsonResultDeserializer())
                .create();

        return new MultipartRequest<GsonResult>
                (
                        url,
                        new TypeToken<GsonResult>() {
                        }.getType(),
                        gson,
                        listener,
                        errorListener,
                        stringPart,
                        file
                );

    }
}
