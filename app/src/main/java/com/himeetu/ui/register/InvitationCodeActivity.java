package com.himeetu.ui.register;

import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.himeetu.R;
import com.himeetu.app.Api;
import com.himeetu.app.NavHelper;
import com.himeetu.model.GsonResult;
import com.himeetu.ui.base.BaseVolleyActivity;
import com.himeetu.util.LogUtil;
import com.himeetu.util.SoftKeyboardStateHelper;
import com.himeetu.util.ToastUtil;

import java.util.Locale;

/**
 * Created by object1984 on 15/12/14.
 */
public class InvitationCodeActivity extends BaseVolleyActivity implements TextWatcher, View.OnClickListener, SoftKeyboardStateHelper.SoftKeyboardStateListener {
    private static final String TAG = InvitationCodeActivity.class.getSimpleName();
    private EditText invitationEditText;
    private ImageButton clearImageButton;
    private Button startButton;
    private static final int INVITATION_CODE_LENGTH = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isZh()) {
            setContentView(R.layout.activity_invitation_code);
        }else {
            setContentView(R.layout.activity_invitation_code_en);
        }

        setStatusBarColor(R.color.black);
        init();

    }

    @Override
    protected void loadViews() {
        super.loadViews();

        invitationEditText = (EditText)findViewById(R.id.edit_invitation);
        clearImageButton = (ImageButton)findViewById(R.id.btn_code_clear);
        startButton = (Button)findViewById(R.id.btn_start);

    }

    @Override
    protected void setupListeners() {
        super.setupListeners();
        invitationEditText.addTextChangedListener(this);
        startButton.setOnClickListener(this);
        clearImageButton.setOnClickListener(this);
        findViewById(R.id.layout_has_account).setOnClickListener(this);
        findViewById(R.id.text_clause).setOnClickListener(this);
        findViewById(R.id.text_privacy).setOnClickListener(this);
        findViewById(R.id.text_cookie).setOnClickListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if(TextUtils.isEmpty(s)){
            clearImageButton.setVisibility(View.GONE);
            startButton.setEnabled(false);
        }else {
            clearImageButton.setVisibility(View.VISIBLE);

            if(s.length() == INVITATION_CODE_LENGTH ){
                startButton.setEnabled(true);
            }else {
                startButton.setEnabled(false);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start:
                start();
                break;
            case R.id.btn_code_clear:
                clearCode();
                break;
            case R.id.layout_has_account:
                toLoginPage();
                break;
            case R.id.text_clause:
                break;
            case R.id.text_privacy:
                break;
            case R.id.text_cookie:
                break;
        }
    }

    private void clearCode() {
        if(invitationEditText == null){
            return;
        }

        invitationEditText.setText("");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        invitationEditText.clearFocus();
    }

    private void start(){
        startButton.setText(R.string.register_verifying);
        String code = invitationEditText.getText().toString();
        checkInvitationCode(code);
    }

    private void toLoginPage(){
        NavHelper.toLoginPage(this);
        finish();
    }


    private void checkInvitationCode(String code) {
        Api.checkInvitationCode( "checkInvitationCode", code, this, this);
    }


    @Override
    public void onErrorResponse(VolleyError error, String tag) {
        super.onErrorResponse(error, tag);

        LogUtil.d(TAG, "onErrorResponse.TAG=" + tag);
        startButton.setText(R.string.register_start);
    }


    @Override
    public void onResponse(GsonResult gsonResult, String tag) {
        LogUtil.d(TAG, "onResponse.TAG=" + tag);

        if(gsonResult.getCode() == 1){
            ToastUtil.show(getString(R.string.register_invitation_code_error));
            startButton.setText(R.string.register_start);
        }else if(gsonResult.getCode() == 0){
            NavHelper.toRegisterPage(this);
        }
    }

    @Override
    public void onSoftKeyboardOpened(int keyboardHeightInPx) {
//        ToastUtil.show("打开");
    }

    @Override
    public void onSoftKeyboardClosed() {
//        ToastUtil.show("关闭");
    }

    private boolean isZh() {
        Locale locale = getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        if (language.endsWith("zh"))
            return true;
        else
            return false;
    }
}
