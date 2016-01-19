package com.himeetu.network.volley;

import android.support.annotation.NonNull;

import com.android.internal.http.multipart.MultipartEntity;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.util.CharsetUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

/**
 * Created by object1984 on 16/1/20.
 */
public class MultipartRequest<T> extends Request<T> {
    private MultipartEntityBuilder builder = MultipartEntityBuilder.create(); ;
//    private MultipartEntity entity = new MultipartEntity();

    private static final String FILE_PART_NAME = "file";
    private static final String STRING_PART_NAME = "name";

    private final Response.Listener<T> mListener;
    private final File mFilePart;
    private final String mStringPart;
    private final Gson gson;
    private final Type type;
    private HttpEntity entity;


    public MultipartRequest(String url, @NonNull final Type type,
                            @NonNull final Gson gson,  Response.Listener<T> listener, Response.ErrorListener errorListener,  String stringPart,  File file)
    {
        super(Method.POST, url, errorListener);
        mListener = listener;
        mFilePart = file;
        mStringPart = stringPart;
        this.gson = gson;
        this.type = type;

        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        try {
            builder.setCharset(CharsetUtils.get("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        buildMultipartEntity();
    }

    private void buildMultipartEntity()
    {

        if (mStringPart != null) {
            builder.addPart(FILE_PART_NAME, new FileBody(mFilePart, ContentType.create("image/jpeg"), mFilePart.getName()));
        }
//        try
//        {
//            builder.addPart(STRING_PART_NAME, new StringBody(mStringPart));
//            builder.addPart("size", new StringBody(mFilePart.length()+ ""));
//            builder.addPart("type", new StringBody("image/jpg"));
//        }
//        catch (UnsupportedEncodingException e)
//        {
//            VolleyLog.e("UnsupportedEncodingException");
//        }

        entity =  builder.build();
    }

    @Override
    public String getBodyContentType()
    {
        return entity.getContentType().getValue();
    }

    @Override
    public byte[] getBody() throws AuthFailureError
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try
        {
            entity.writeTo(bos);
        }
        catch (IOException e)
        {
            VolleyLog.e("IOException writing to ByteArrayOutputStream");
        }
        return bos.toByteArray();
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response)
    {
        String tag = (String)getTag();
        try
        {

            String json = new String(response.data, "utf-8");


            return (Response<T>) Response.success
                    (
                            gson.fromJson(json, type),
                            HttpHeaderParser.parseCacheHeaders(response),
                            tag
                    );
        }
        catch (UnsupportedEncodingException e)
        {
            return Response.error(new ParseError(e), tag);
        }
        catch (JsonSyntaxException e)
        {
            return Response.error(new ParseError(e), tag);
        }
    }

    @Override
    protected void deliverResponse(T response)
    {
        mListener.onResponse(response, (String)getTag());
    }
}
