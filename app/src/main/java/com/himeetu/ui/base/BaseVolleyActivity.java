package com.himeetu.ui.base;

import android.os.Bundle;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.himeetu.model.GsonResult;
import com.himeetu.util.ToastUtil;

public abstract class BaseVolleyActivity extends BaseActivity implements Response.Listener<GsonResult>, Response.ErrorListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResponse(GsonResult response, String tag) {

    }

    @Override
    public void onErrorResponse(VolleyError error, String tag) {
        ToastUtil.show(error.getLocalizedMessage());
    }
}
