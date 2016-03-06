package com.himeetu.ui.register;

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
import com.himeetu.app.NavHelper;
import com.himeetu.model.GsonResult;
import com.himeetu.network.dic.Argument;
import com.himeetu.ui.base.BaseActivity;
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
public class IdentityActivity extends BaseVolleyActivity implements View.OnClickListener {
    private static final String TAG = IdentityActivity.class.getSimpleName();

    private static final String TAG_API_CHECK_NICKNAME = "TAG_API_CHECK_NICKNAME";
    private static final String TAG_API_USER_REGISTER = "TAG_API_USER_REGISTER";
    private TextView birthdayTextView;
    private TextView sexTextView;
    private TimePickerView birthdayTimePickerView;
    private OptionsPickerView sexPickerView;
    private EditText nicknameEditText;
    private EditText safeQuestionEditText;
    private EditText safeAnswerEditText;
    private Button nextButton;
    private ArrayList<String> sexItems = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setThemeTranslucent();
        setContentView(R.layout.activity_identity);
        setupToolbar(false, R.string.your_identity);
        init();
    }

    @Override
    protected void loadViews() {
        super.loadViews();
        birthdayTextView = (TextView) findViewById(R.id.text_birthday);
        sexTextView = (TextView) findViewById(R.id.text_sex);
        nicknameEditText = (EditText)findViewById(R.id.edit_nickname);
        safeQuestionEditText = (EditText)findViewById(R.id.edit_safe_question);
        safeAnswerEditText = (EditText)findViewById(R.id.edit_safe_answer);
        nextButton = (Button)findViewById(R.id.btn_next);


    }

    @Override
    protected void setupListeners() {
        super.setupListeners();
        birthdayTextView.setOnClickListener(this);
        sexTextView.setOnClickListener(this);
        nextButton.setOnClickListener(this);

        nicknameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String nickname = nicknameEditText.getText().toString().trim();
                    checkNickName(nickname);
                }
            }
        });

        birthdayTimePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                birthdayTextView.setText(DateUtils.format(date));
            }
        });

        sexPickerView.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                sexTextView.setText(sexItems.get(options1));
            }
        });

        findViewById(R.id.layout_has_account).setOnClickListener(this);
    }

    private void checkNickName(String nickname) {
        Api.checkNickname(TAG_API_CHECK_NICKNAME, nickname, this, this);
    }

    @Override
    protected void initViews() {
        super.initViews();
        birthdayTextView.setText(DateUtils.format(new Date()));

        birthdayTimePickerView = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        birthdayTimePickerView.setCancelable(true);
        birthdayTimePickerView.setCyclic(false);
        birthdayTimePickerView.setRange(1920, 2020);
        birthdayTimePickerView.setTime(new Date());

        sexItems.add("男");
        sexItems.add("女");

        sexPickerView = new OptionsPickerView(this);

        sexPickerView.setPicker(sexItems);
        sexPickerView.setCyclic(false);
        sexPickerView.setCancelable(true);
    }

    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.text_birthday:
                 birthdayTimePickerView.show();
                 break;
             case R.id.text_sex:
                 sexPickerView.show();
                 break;
             case R.id.btn_next:
                 doRegister();
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

   private void  doRegister(){
       String username = getIntent().getStringExtra(Argument.USERNAME);
       String password = getIntent().getStringExtra(Argument.PASSWORD);
       int countryCode = getIntent().getIntExtra(Argument.COUNTRY_CODE, 0);

        String nickName = nicknameEditText.getText().toString().trim();
        int sex = sexTextView.getText().toString().trim().equals("男") ? 0 : 1;
        String birthday = birthdayTextView.getText().toString().trim();
        String safeCode = safeQuestionEditText.getText().toString().trim() + safeAnswerEditText.getText().toString().trim();

       try {
           Api.userRegister(TAG_API_USER_REGISTER, username, password, username,  URLEncoder.encode(safeCode, "utf-8"), nickName, birthday, countryCode, sex, this, this);
       } catch (UnsupportedEncodingException e) {
           e.printStackTrace();
       }
   }

    @Override
    public void onResponse(GsonResult response, String tag) {
        super.onResponse(response, tag);
        if(TAG_API_CHECK_NICKNAME.equals(tag)){
            int code = response.getCode();
            if(code == 1){
                nextButton.setEnabled(true);
            }else {
                nextButton.setEnabled(false);
            }
        }

        if(TAG_API_USER_REGISTER.equals(tag)){
            //参数说明：0 表示成功，2 表示角色名已存在，3 表示账号名已存在，-1 表示系统故障
            int code = response.getCode();
            switch (code){
                case 0:
                    onRegisterSuccess();
                    break;
                case 2:
                    ToastUtil.show("昵称已经存在");
                    break;
                case 3:
                    ToastUtil.show("帐号名已经存在");
                    break;
                case -1:
                    ToastUtil.show("系统故障，请稍后重试。");
                    break;
            }
        }
    }

    private void onRegisterSuccess(){
        ToastUtil.show("注册成功");
        NavHelper.toMainPage(this);
        finish();
    }

    @Override
    public void onErrorResponse(VolleyError error, String tag) {
        super.onErrorResponse(error, tag);
        if(TAG_API_CHECK_NICKNAME.equals(tag)){
            nextButton.setEnabled(false);
        }

        if(TAG_API_USER_REGISTER.equals(tag)){
            ToastUtil.show("注册失败");
        }
    }
}
