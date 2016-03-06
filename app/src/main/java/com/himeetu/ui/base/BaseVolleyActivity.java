package com.himeetu.ui.base;

import android.content.Intent;
import android.os.Bundle;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.himeetu.app.MEETApplication;
import com.himeetu.model.GsonResult;
import com.himeetu.ui.login.LoginActivity;
import com.himeetu.util.ToastUtil;

public abstract class BaseVolleyActivity extends BaseActivity implements Response.Listener<GsonResult>, Response.ErrorListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResponse(GsonResult response, String tag) {
        int code = response.getCode();
        //Result： 0 表示成功，1 参数错误，2 未登录，3 权限不够，4 重复操作
        switch (code){
            case 0:
                break;
            case 1:
                ToastUtil.show("参数错误");
                break;
            case 2:
                ToastUtil.show("未登录");
                Intent intent = new Intent(BaseVolleyActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
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
        ToastUtil.show(error.getLocalizedMessage());


    }
}
