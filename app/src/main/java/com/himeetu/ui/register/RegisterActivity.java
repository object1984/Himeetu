package com.himeetu.ui.register;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.himeetu.R;
import com.himeetu.app.Api;
import com.himeetu.app.NavHelper;
import com.himeetu.model.GsonResult;
import com.himeetu.ui.base.BaseActivity;
import com.himeetu.ui.base.BaseVolleyActivity;
import com.himeetu.ui.base.StatusBarCompat;
import com.himeetu.util.LogUtil;
import com.himeetu.util.ValidateUtil;

/**
 * Created by object1984 on 15/12/2.
 */
public class RegisterActivity extends BaseVolleyActivity implements View.OnClickListener {
    private static final String TAG = RegisterActivity.class.getSimpleName();

    private ImageButton passwordInputEyeButton;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private ImageView usernameLoadingImageView;
    private AnimationDrawable usernameLoadingAnimationDrawable;
    private ImageButton usernameClearImageButton;
    private ImageButton usernameRightImageButton;

    private Button nextButton;
    private boolean usernameUsed = true;

    private static final String TAG_API_CHECK_USERNAME = "TAG_API_CHECK_USERNAME";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setThemeTranslucent();

        setContentView(R.layout.activity_register);
        setupToolbar(false, R.string.register);
        init();
    }

    @Override
    protected void loadViews() {
        super.loadViews();
        passwordInputEyeButton = (ImageButton)findViewById(R.id.btn_input_eye);
        usernameEditText = (EditText)findViewById(R.id.edit_username);
        passwordEditText = (EditText)findViewById(R.id.edit_password);
        usernameLoadingImageView = (ImageView)findViewById(R.id.img_username_loading);
        usernameClearImageButton = (ImageButton)findViewById(R.id.btn_username_clear);
        usernameRightImageButton = (ImageButton)findViewById(R.id.btn_username_right);
        nextButton = (Button)findViewById(R.id.btn_next);
    }

    @Override
    protected void setupListeners() {
        super.setupListeners();

        passwordInputEyeButton.setOnClickListener(this);
        usernameClearImageButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        usernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(TextUtils.isEmpty(s)){
                    usernameClearImageButton.setVisibility(View.GONE);

                }else {
                    usernameClearImageButton.setVisibility(View.VISIBLE);
                }
                nextButton.setEnabled(false);
                usernameRightImageButton.setVisibility(View.GONE);
            }
        });

        usernameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                usernameRightImageButton.setVisibility(View.GONE);
                if(!hasFocus){
                    usernameLoadingImageView.setVisibility(View.VISIBLE);
                    usernameClearImageButton.setVisibility(View.GONE);
                    usernameLoadingAnimationDrawable = (AnimationDrawable) usernameLoadingImageView.getDrawable();
                    usernameLoadingAnimationDrawable.start();

                    String username = usernameEditText.getText().toString().trim();

                    checkUsername(username);
                }
            }
        });

        passwordEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String password = passwordEditText.getText().toString().trim();

                }
            }
        });

        findViewById(R.id.layout_has_account).setOnClickListener(this);
    }

    @Override
    protected void initViews() {
        super.initViews();


    }

    private void checkUsername(String username){
        if(ValidateUtil.checkUserName(username)){
            Api.checkUsername(TAG_API_CHECK_USERNAME, username, this, this);
        }else {
            nextButton.setEnabled(false);
            usernameClearImageButton.setVisibility(View.VISIBLE);
            usernameLoadingAnimationDrawable.stop();
            usernameLoadingImageView.setVisibility(View.GONE);
        }
    }

    private void checkPassword(String password){
        if(ValidateUtil.checkPassword(password) && !usernameUsed){
            nextButton.setEnabled(true);
        }else {
            nextButton.setEnabled(false);
        }
    }

    private void toCountryChoose(){
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        checkPassword(password);

        NavHelper.toCountryChoosePage(this, username, password);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_input_eye:
                togglePasswordVisible();
                break;
            case R.id.btn_username_clear:
                clearUserName();
                break;
            case R.id.btn_next:
                toCountryChoose();
                break;
            case R.id.layout_has_account:
                toLoginPage();
                break;
        }
    }

    private void toLoginPage(){
        NavHelper.toLoginPage(this);
        finish();
    }

    private void clearUserName() {
        if(usernameEditText == null){
            return;
        }

        usernameEditText.setText("");
    }


    private void togglePasswordVisible(){
        if(passwordInputEyeButton.isSelected()){
            passwordInputEyeButton.setSelected(false);
            passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }else {
            passwordInputEyeButton.setSelected(true);
            passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT);
        }

    }


    @Override
    public void onResponse(GsonResult response, String tag) {
        super.onResponse(response, tag);
        LogUtil.d(TAG, "onResponse, TAG=" + tag);
        usernameLoadingAnimationDrawable.stop();
        usernameLoadingImageView.setVisibility(View.GONE);
        if(TAG_API_CHECK_USERNAME.equals(tag)){
            int code = response.getCode();
            if(code == 1){//不存在
                usernameClearImageButton.setVisibility(View.GONE);
                usernameRightImageButton.setVisibility(View.VISIBLE);
                usernameUsed = false;
                nextButton.setEnabled(true);
            }else {
                usernameClearImageButton.setVisibility(View.VISIBLE);
                usernameRightImageButton.setVisibility(View.GONE);
                usernameUsed = true;
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error, String tag) {
        super.onErrorResponse(error, tag);
        LogUtil.d(TAG, "onErrorResponse, TAG=" + tag);
        usernameLoadingAnimationDrawable.stop();
        usernameRightImageButton.setVisibility(View.GONE);
        usernameUsed = true;
    }
}
