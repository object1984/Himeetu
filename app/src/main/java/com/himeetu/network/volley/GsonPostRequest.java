package com.himeetu.network.volley;

import android.support.annotation.NonNull;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.himeetu.util.JsonUtil;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Makes a post request and converts the response from JsonElement into a
 * list of objects/object using with Google Gson.
 */
public class GsonPostRequest<T> extends JsonRequest<T>
{
    private final Gson gson;
    private final Type type;
    private final Response.Listener<T> listener;
    private String body;

    //String.format("application/json; charset=%s", PROTOCOL_CHARSET);


    @Override
    public String getBodyContentType() {
        return String.format("application/x-www-form-urlencoded; charset=%s", PROTOCOL_CHARSET);
    }public GsonPostRequest
    (
            @NonNull final String url,
            @NonNull final String body,
            @NonNull final Type type,
            @NonNull final Gson gson,
            @NonNull final Response.Listener<T> listener,
            @NonNull final Response.ErrorListener errorListener
    )
    {
        super(Method.POST, url, body, listener, errorListener);

        this.gson = gson;
        this.type = type;
        this.listener = listener;
        this.body = body;
    }

    @Override
    protected Map<String, String> getParams() {
        //在这里设置需要post的参数
        Map<String, String> params = new HashMap<String, String>();
        JSONObject bodyJsonObj = JsonUtil.getJSONObject(body);
        Iterator<String> it = bodyJsonObj.keys();
        while (it.hasNext()){
            String key = it.next();
            params.put(key, JsonUtil.getString(bodyJsonObj, key));
        }

        return params;
    }

    @Override
    protected void deliverResponse(T response)
    {
        listener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response)
    {
        try
        {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));

            return (Response<T>) Response.success
                    (
                            gson.fromJson(json, type),
                            HttpHeaderParser.parseCacheHeaders(response)
                    );
        }
        catch (UnsupportedEncodingException e)
        {
            return Response.error(new ParseError(e));
        }
        catch (JsonSyntaxException e)
        {
            return Response.error(new ParseError(e));
        }
    }
}
