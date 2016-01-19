package com.himeetu.network.volley;

import android.support.annotation.NonNull;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.himeetu.network.http.MultiPartRequest;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by object1984 on 16/1/19.
 */
public class MultiPartGsonRequest<T> extends JsonRequest<T> implements MultiPartRequest {
    private final Gson gson;
    private final Type type;
    private final Response.Listener<T> mListener;
    /* To hold the parameter name and the File to upload */
    private Map<String,File> fileUploads = new HashMap<String,File>();

    /* To hold the parameter name and the string content to upload */
    private Map<String,String> stringUploads = new HashMap<String,String>();

    /**
     * Creates a new request with the given method.
     *
     * @param url URL to fetch the string at
     * @param listener Listener to receive the String response
     * @param errorListener Error listener, or null to ignore errors
     */
    public MultiPartGsonRequest(String url,
                                  @NonNull final Type type,
                                  @NonNull final Gson gson,
                                    Response.Listener<T> listener,
                                   Response.ErrorListener errorListener) {
        super(Method.POST, url, "",  listener, errorListener);
        this.gson = gson;
        this.type = type;
        mListener = listener;
    }



    public void addFileUpload(String param,File file) {
        fileUploads.put(param,file);
    }

    public void addStringUpload(String param,String content) {
        stringUploads.put(param,content);
    }

    /**
     * 要上传的文件
     */
    public Map<String,File> getFileUploads() {
        return fileUploads;
    }

    /**
     * 要上传的参数
     */
    public Map<String,String> getStringUploads() {
        return stringUploads;
    }


    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try
        {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));

            return (Response<T>) Response.success
                    (
                            gson.fromJson(json, type),
                            HttpHeaderParser.parseCacheHeaders(response),
                            (String) getTag()
                    );
        }
        catch (UnsupportedEncodingException e)
        {
            return Response.error(new ParseError(e), (String)getTag());
        }
        catch (JsonSyntaxException e)
        {
            return Response.error(new ParseError(e), (String)getTag());
        }
    }

    @Override
    protected void deliverResponse(T response) {

    }


    /**
     * 空表示不上传
     */
    public String getBodyContentType() {
        return null;
    }
}