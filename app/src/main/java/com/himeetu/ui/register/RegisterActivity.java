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

import com.himeetu.R;
import com.himeetu.app.NavHelper;
import com.himeetu.ui.base.BaseActivity;
import com.himeetu.ui.base.StatusBarCompat;
import com.himeetu.util.ValidateUtil;

/**
 * Created by object1984 on 15/12/2.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton passwordInputEyeButton;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private ImageView usernameLoadingImageView;
    private AnimationDrawable usernameLoadingAnimationDrawable;
    private ImageButton usernameClearImageButton;

    private Button nextButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setThemeTranslucent();

        setContentView(R.layout.activity_register);

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
            }
        });

        usernameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String username = usernameEditText.getText().toString().trim();

                    if(ValidateUtil.checkUserName(username)){
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                nextButton.setEnabled(true);
                            }
                        }, 2000);
                    }else {
                        nextButton.setEnabled(false);
                    }
                }
            }
        });
    }

    @Override
    protected void initViews() {
        super.initViews();

        setTitleText(R.string.register);

        usernameLoadingAnimationDrawable = (AnimationDrawable) usernameLoadingImageView.getDrawable();
        usernameLoadingAnimationDrawable.start();
    }

    private void toRegister(){
        //Calls a new Activity
        startActivity(new Intent(this, CountryChooseActivity.class));

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
                toNext();
                break;
        }
//        toRegister();
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

    private void toNext(){
        NavHelper.toCountryChoosePage(this);
    }
}
