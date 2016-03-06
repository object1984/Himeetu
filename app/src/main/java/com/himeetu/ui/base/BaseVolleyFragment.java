package com.himeetu.ui.base;

import android.content.Intent;
import android.opengl.GLException;
import android.os.Bundle;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.himeetu.model.GsonResult;
import com.himeetu.ui.login.LoginActivity;
import com.himeetu.util.LogUtil;
import com.himeetu.util.ToastUtil;

public abstract class BaseVolleyFragment extends BaseFragment implements Response.Listener<GsonResult>, Response.ErrorListener{
    protected boolean isLogin = true;
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

        int code = response.getCode();
        //Result： 0 表示成功，1 参数错误，2 未登录，3 权限不够，4 重复操作
        switch (code){
            case 0:
                break;
            case 1:
                ToastUtil.show("参数错误");
                break;
            case 2:
                isLogin = false;

                return;
            case 3:
                ToastUtil.show("权限不够");
                break;
            case 4:
                ToastUtil.show("重复操作");
                break;
        }
    }

    @Override
    public void onErrorResponse(VolleyError error, String tag) {
        LogUtil.d("BaseVolleyFragment.onResponse tag＝" + tag, error.getLocalizedMessage());
        ToastUtil.show(error.getLocalizedMessage());
    }
}
