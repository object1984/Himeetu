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
public class ApiRequests
{
    /**
     * Returns a dummy object
     *
     * @param listener is the listener for the correct answer
     * @param errorListener is the listener for the error response
     *
     * @return {@link GsonGetRequest}
     */
    public static GsonGetRequest<GsonResult> getAllMessage
    (
            @NonNull final Response.Listener<GsonResult> listener,
            @NonNull final Response.ErrorListener errorListener
    )
    {
        final String url = String.format(UrlPatten.URL_GET_COURSE_ALL);
        LogUtil.d("url", url);
        final Gson gson = new GsonBuilder()
                .registerTypeAdapter(GsonResult.class, new GsonResultDeserializer())
                .create();

        return new GsonGetRequest<GsonResult>
                (
                        url,
                        new TypeToken<GsonResult>() {}.getType(),
                        gson,
                        listener,
                        errorListener
                );
    }

    public static GsonGetRequest<GsonResult> getQuestionById
            (
                    @NonNull final String id,
                    @NonNull final Response.Listener<GsonResult> listener,
                    @NonNull final Response.ErrorListener errorListener
            )
    {
        final String url = String.format(UrlPatten.URL_GET_QUESTION_BY_ID, id);
        LogUtil.d("url", url);
        final Gson gson = new GsonBuilder()
                .registerTypeAdapter(GsonResult.class, new GsonResultDeserializer())
                .create();

        return new GsonGetRequest<GsonResult>
                (
                        url,
                        new TypeToken<GsonResult>() {}.getType(),
                        gson,
                        listener,
                        errorListener
                );
    }


}
