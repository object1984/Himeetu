package com.himeetu.ui.base;

import android.os.Bundle;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.himeetu.model.GsonResult;
import com.himeetu.util.LogUtil;
import com.himeetu.util.ToastUtil;

public abstract class BaseVolleyFragment extends BaseFragment implements Response.Listener<GsonResult>, Response.ErrorListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResponse(GsonResult response, String tag) {
        if(response == null){
            LogUtil.d("BaseVolleyFragment.onResponse tag＝" + tag, "response=null");
        }else {
            LogUtil.d("BaseVolleyFragment.onResponse tag＝" + tag, response.getJsonStr());
        }

    }

    @Override
    public void onErrorResponse(VolleyError error, String tag) {
        LogUtil.d("BaseVolleyFragment.onResponse tag＝" + tag, error.getLocalizedMessage());
        ToastUtil.show(error.getLocalizedMessage());
    }
}
