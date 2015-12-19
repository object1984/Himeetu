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
import com.himeetu.util.LogUtil;
import com.himeetu.util.ToastUtil;
import com.himeetu.util.ValidateUtil;

/**
 * Created by object1984 on 15/12/2.
 */
public class LoginActivity extends BaseVolleyActivity implements View.OnClickListener {
    private final String TAG = LoginActivity.class.getSimpleName();
    private final String TAG_API_USER_LOGIN = "TAG_API_USER_LOGIN";

    private EditText userNameEditText;
    private EditText passwordEditText;

    private Button loginButton;
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
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_register:
                toRegister();
                break;
            case R.id.btn_login:
                toLogin();
                break;
        }
    }

    private void toLogin() {
        String username = userNameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if(ValidateUtil.checkUserName(username) && ValidateUtil.checkPassword(password)){
            Api.userLogin(TAG_API_USER_LOGIN, username, password, this, this);
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

        if(TAG_API_USER_LOGIN.equals(tag)){
            int code = response.getCode();
            //  0 为登陆成功，1 为密码错误，2 为账号被禁用，不可用状态，3 为账号不存在
            switch (code){
                case 0:
                    onLoginSuccess();
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
    }

    @Override
    public void onErrorResponse(VolleyError error, String tag) {
        super.onErrorResponse(error, tag);

        if(TAG_API_USER_LOGIN.equals(tag)){

        }
    }

    private void onLoginSuccess(){
        NavHelper.toMainPage(this);
    }
}
