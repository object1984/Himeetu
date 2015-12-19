package com.himeetu.ui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.himeetu.R;
import com.himeetu.app.Api;
import com.himeetu.model.GsonResult;
import com.himeetu.network.dic.Argument;
import com.himeetu.ui.base.BaseVolleyActivity;
import com.himeetu.util.DateUtils;
import com.himeetu.util.ToastUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by object1984 on 15/12/17.
 */
public class FindPasswordActivity extends BaseVolleyActivity implements View.OnClickListener {
    private static final String TAG = FindPasswordActivity.class.getSimpleName();

    private static final String TAG_API_FIND_PASSWORD = "TAG_API_FIND_PASSWORD";

    private EditText nicknameEditText;
    private EditText safeQuestionEditText;
    private EditText safeAnswerEditText;
    private Button nextButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setThemeTranslucent();
        setContentView(R.layout.activity_find_password);
        setupToolbar(true, R.string.find_password);
        init();
    }

    @Override
    protected void loadViews() {
        super.loadViews();
        nicknameEditText = (EditText)findViewById(R.id.edit_nickname);
        safeQuestionEditText = (EditText)findViewById(R.id.edit_safe_question);
        safeAnswerEditText = (EditText)findViewById(R.id.edit_safe_answer);
        nextButton = (Button)findViewById(R.id.btn_next);


    }

    @Override
    protected void setupListeners() {
        super.setupListeners();
        nextButton.setOnClickListener(this);

    }

    @Override
    protected void initViews() {
        super.initViews();
    }

    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.btn_next:
                 doRegister();
         }
    }

   private void  doRegister(){
        String nickName = nicknameEditText.getText().toString().trim();
        String safeCode = safeQuestionEditText.getText().toString().trim() + safeAnswerEditText.getText().toString().trim();

//       try {
//           Api.userRegister(TAG_API_FIND_PASSWORD, username,  URLEncoder.encode(safeCode, "utf-8"), this, this);
//       } catch (UnsupportedEncodingException e) {
//           e.printStackTrace();
//       }
   }

    @Override
    public void onResponse(GsonResult response, String tag) {
        super.onResponse(response, tag);


    }

    private void onRegisterSuccess(){

    }

    @Override
    public void onErrorResponse(VolleyError error, String tag) {
        super.onErrorResponse(error, tag);

    }
}
