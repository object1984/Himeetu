package com.himeetu.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.CollapsibleActionView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.VolleyError;
import com.himeetu.R;
import com.himeetu.app.Api;
import com.himeetu.app.NavHelper;
import com.himeetu.model.GsonResult;
import com.himeetu.ui.base.BaseActivity;
import com.himeetu.ui.base.BaseVolleyActivity;
import com.himeetu.ui.register.InvitationCodeActivity;
import com.himeetu.util.JsonUtil;
import com.himeetu.util.LogUtil;
import com.himeetu.util.ToastUtil;
import com.himeetu.util.ValidateUtil;

import org.json.JSONObject;

/**
 * Created by object1984 on 15/12/2.
 */
public class LoginActivity extends BaseVolleyActivity implements View.OnClickListener {
    private final String TAG = LoginActivity.class.getSimpleName();
    private final String TAG_API_USER_LOGIN_STEP_1 = "TAG_API_USER_LOGIN_STEP_1";
    private final String TAG_API_USER_LOGIN_STEP_2 = "TAG_API_USER_LOGIN_STEP_2";

    private EditText userNameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setThemeTranslucent();
        setContentView(R.layout.activity_login);

        init();
    }

    @Override
    protected void loadViews() {
        super.loadViews();
        userNameEditText = (EditText)findViewById(R.id.edit_username);
        passwordEditText = (EditText)findViewById(R.id.edit_password);
    }

    @Override
    protected void setupListeners() {
        super.setupListeners();
        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.text_register).setOnClickListener(this);
        findViewById(R.id.text_find_password).setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_register:
                toRegister();
                break;
            case R.id.text_find_password:
                toFindPassword();
                break;
            case R.id.btn_login:
                toLogin();
                break;
        }
    }

    private void toFindPassword() {
        NavHelper.toFindPassowrdPage(this);
    }

    private String username;
    private String password;
    private void toLogin() {
         username = userNameEditText.getText().toString().trim();
         password = passwordEditText.getText().toString().trim();

        if(ValidateUtil.checkUserName(username) && ValidateUtil.checkPassword(password)){
            Api.userLoginStep1(TAG_API_USER_LOGIN_STEP_1, username, password, this, this);
        }
    }

    private void toRegister(){
        startActivity(new Intent(this, InvitationCodeActivity.class));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtil.d(TAG, "onNewIntent");


    }

    @Override
    public void onResponse(GsonResult response, String tag) {
        super.onResponse(response, tag);

        if(TAG_API_USER_LOGIN_STEP_1.equals(tag)){
            int code = response.getCode();
            //  0 为登陆成功，1 为密码错误，2 为账号被禁用，不可用状态，3 为账号不存在
            switch (code){
                case 0:
                    JSONObject jsonObject = JsonUtil.getJSONObject(response.getJsonStr());
                    if(jsonObject != null){
                        String key = JsonUtil.getString(jsonObject, "key");
                        int uid = JsonUtil.getInt(jsonObject, "id");
                        int time = JsonUtil.getInt(jsonObject, "time");
                        int type = 0; //default = 0, 预留参数
                        Api.userLoginStep2(TAG_API_USER_LOGIN_STEP_2, key, uid, time, type, this, this);
                    }
                    break;
                case 1:
                    ToastUtil.show("密码错误");
                    break;
                case 2:
                    ToastUtil.show("账号被禁用");
                    break;
                case 3:
                    ToastUtil.show("账号不存在");
                    break;
            }
        }

        if(TAG_API_USER_LOGIN_STEP_2.equals(tag)){
            int code = response.getCode();
            //result: 0 表示成功，1 表示参数错误失败，2 未登录，3 权限不足；
            switch (code){
                case 0:
                    onLoginSuccess();
                    break;
                case 1:
                    ToastUtil.show("参数错误");
                    break;
                case 2:
                    ToastUtil.show("未登录");
                    break;
                case 3:
                    ToastUtil.show("权限不足");
                    break;
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error, String tag) {
        super.onErrorResponse(error, tag);

        if(TAG_API_USER_LOGIN_STEP_1.equals(tag)){

        }
    }

    private void onLoginSuccess(){
        NavHelper.toMainPage(this, username);
    }
}
