package com.himeetu.ui.setup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.himeetu.R;
import com.himeetu.app.Api;
import com.himeetu.app.NavHelper;
import com.himeetu.model.GsonResult;
import com.himeetu.model.User;
import com.himeetu.model.service.UserService;
import com.himeetu.ui.base.BaseActivity;
import com.himeetu.ui.base.BaseVolleyActivity;
import com.himeetu.util.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 密码修改
 */
public class EditPassWordActivity extends BaseVolleyActivity implements View.OnClickListener{

    private final String TAG_UPDATE_USER_PASSWORD = "TAG_UPDATE_USER_PASSWORD";
    private EditText etOldPwd,etNewPwd,etNewPwdConfirm;
    private ImageView ivOldPwd,ivNewPwd,ivNewPwdConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pass_word);

        initToolBar();

        super.init();
    }

    @Override
    protected void loadViews() {
        super.loadViews();

        etOldPwd = (EditText) findViewById(R.id.et_old_pwd);
        etNewPwd = (EditText) findViewById(R.id.et_new_pwd);
        etNewPwdConfirm = (EditText) findViewById(R.id.et_new_pwd_confirm);

        ivOldPwd = (ImageView) findViewById(R.id.iv_old_pwd);
        ivNewPwd = (ImageView) findViewById(R.id.iv_new_pwd);
        ivNewPwdConfirm = (ImageView) findViewById(R.id.iv_new_pwd_confirm);

    }

    @Override
    protected void initViews() {
        super.initViews();

        etOldPwd.setInputType(0x81);
        etNewPwd.setInputType(0x81);
        etNewPwdConfirm.setInputType(0x81);
    }

    @Override
    protected void setupListeners() {
        super.setupListeners();

        ivOldPwd.setOnClickListener(this);
        ivNewPwd.setOnClickListener(this);
        ivNewPwdConfirm.setOnClickListener(this);
    }

    private void initToolBar() {
        setupToolbar(true,R.string.set_password);
        setToolBarColor(getResources().getColor(R.color.white));
        setRightTextAndVisible(R.string.commit, View.VISIBLE);
        setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //提交

                commit();
            }
        });
    }

    private void commit(){

        String name = UserService.get().getUsername();

        String oldPwd = etOldPwd.getText().toString();

        String newPwd = etNewPwd.getText().toString();

        String NewPwdConfirm = etNewPwdConfirm.getText().toString();

        if(TextUtils.isEmpty(oldPwd) || TextUtils.isEmpty(newPwd) || TextUtils.isEmpty(NewPwdConfirm)){

            ToastUtil.show(getString(R.string.pwd_not_null));

            return;
        }

        if(!newPwd.equals(NewPwdConfirm)){

            ToastUtil.show(getString(R.string.pwd_confrim_error));

            return;

        }

        Api.updatePassWord(TAG_UPDATE_USER_PASSWORD,name,oldPwd,newPwd,this,this);

    }

    @Override
    public void onResponse(GsonResult response, String tag) {
        super.onResponse(response, tag);

        if (response.getCode() == 0) {

            ToastUtil.show(getString(R.string.success));
            NavHelper.finishWithAnim(EditPassWordActivity.this);
        } else {

            ToastUtil.show(response.getMsg());
        }
    }

    @Override
    public void onErrorResponse(VolleyError error, String tag) {
        super.onErrorResponse(error, tag);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.iv_old_pwd:

                boolean isHide = ivOldPwd.isSelected();

                etOldPwd.setInputType(isHide?0x81:0x90);

                ivOldPwd.setSelected(!isHide);

                break;

            case R.id.iv_new_pwd:

                isHide = ivNewPwd.isSelected();

                etNewPwd.setInputType(isHide?0x81:0x90);

                ivNewPwd.setSelected(!isHide);


                break;

            case R.id.iv_new_pwd_confirm:

                isHide = ivNewPwdConfirm.isSelected();

                etNewPwdConfirm.setInputType(isHide?0x81:0x90);

                ivNewPwdConfirm.setSelected(!isHide);

                break;



        }
    }
}
