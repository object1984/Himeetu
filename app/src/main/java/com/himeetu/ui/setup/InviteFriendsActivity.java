package com.himeetu.ui.setup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.himeetu.R;
import com.himeetu.app.Api;
import com.himeetu.app.NavHelper;
import com.himeetu.model.GsonResult;
import com.himeetu.model.service.UserService;
import com.himeetu.ui.base.BaseActivity;
import com.himeetu.ui.base.BaseVolleyActivity;
import com.himeetu.util.TextViewUtil;
import com.himeetu.util.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 邀请码
 */
public class InviteFriendsActivity extends BaseVolleyActivity implements View.OnClickListener {
    private TextView tv_id;
    private ImageView ivBack;
    private TextView tvTitle;
    private TextView tvRightText;
    private TextView tvInviteCode;
    private String TAG_INVITE_FRIENDS_CODE = "TAG_INVITE_FRIENDS_CODE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_friends);
        super.init();
        tv_id = (TextView) findViewById(R.id.tv_id);
        tvInviteCode = (TextView) findViewById(R.id.tv_id);

        getInviteCode();

    }

    private void getInviteCode() {
        String name = UserService.get().getUsername();

        Api.getInviteCode(TAG_INVITE_FRIENDS_CODE, name, this, this);
    }


    @Override
    protected void loadViews() {
        super.loadViews();

        ivBack = (ImageView) findViewById(R.id.toolbar_back);
        tvTitle = (TextView) findViewById(R.id.toolbar_title);
        tvRightText = (TextView) findViewById(R.id.toolbar_right_text);


    }

    @Override
    protected void initViews() {
        super.initViews();

        tvTitle.setText(getString(R.string.invitation));
        tvRightText.setText(getString(R.string.share));


    }

    @Override
    protected void setupListeners() {
        super.setupListeners();

        ivBack.setOnClickListener(this);
        tvRightText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.toolbar_back:
                NavHelper.finishWithAnim(InviteFriendsActivity.this);
                onBackPressed();

                break;
            case R.id.toolbar_right_text:  //分享


                break;
        }


    }

    @Override
    public void onResponse(GsonResult response, String tag) {
        super.onResponse(response, tag);


        if (response.getCode() == 0) {
            try {
                JSONObject json = new JSONObject(response.getJsonStr());
                String  code = json.getString("invite");
                if (!TextUtils.isEmpty(code)) {
                    tvInviteCode.setText(code);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            ToastUtil.show(response.getMsg());
        }


    }

    @Override
    public void onErrorResponse(VolleyError error, String tag) {
        super.onErrorResponse(error, tag);
    }
}
