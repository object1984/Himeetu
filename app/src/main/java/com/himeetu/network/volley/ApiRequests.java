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

    public static GsonGetRequest<GsonResult> userLogin(@NonNull final String username,
                                                          @NonNull final String password,
                                                          @NonNull final Response.Listener<GsonResult> listener,
                                                          @NonNull final Response.ErrorListener errorListener) {
        final String url = String.format(UrlPatten.URL_USER_LOGIN, username, password);

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
