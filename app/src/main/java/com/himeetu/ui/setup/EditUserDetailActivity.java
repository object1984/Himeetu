package com.himeetu.ui.setup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.himeetu.R;
import com.himeetu.ui.base.BaseActivity;
import com.himeetu.view.SelectPicPopupWindow;
import android.os.Bundle;
import android.app.Activity;
import android.widget.RelativeLayout;
import com.github.siyamed.shapeimageview.RoundedImageView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 编辑用户资料
 */
public class EditUserDetailActivity extends BaseActivity implements View.OnClickListener {
    private SelectPicPopupWindow menuWindow;
    private RelativeLayout rlUserHead;
    private RoundedImageView rivUserHead;
    private RelativeLayout rlName;
    private TextView tvName;
    private RelativeLayout rlId;
    private TextView tvId;
    private RelativeLayout rlEmail;
    private TextView tvEmail;
    private RelativeLayout rlPhone;
    private TextView tvPhone;
    private RelativeLayout rlSex;
    private TextView tvSex;
    private RelativeLayout rlBirthday;
    private TextView tvBirthday;
    private RelativeLayout rlQuestion;
    private TextView tvQuestion;
    private TextView tvAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_detail);
        super.init();
        initToolBar();

    }


    @Override
    protected void loadViews() {
        super.loadViews();

        rlUserHead = (RelativeLayout) findViewById(R.id.rl_user_head);
        rivUserHead = (RoundedImageView) findViewById(R.id.riv_user_head);
        rlName = (RelativeLayout) findViewById(R.id.rl_name);
        tvName = (TextView) findViewById(R.id.tv_name);
        rlId = (RelativeLayout) findViewById(R.id.rl_id);
        tvId = (TextView) findViewById(R.id.tv_id);
        rlEmail = (RelativeLayout) findViewById(R.id.rl_email);
        tvEmail = (TextView) findViewById(R.id.tv_email);
        rlPhone = (RelativeLayout) findViewById(R.id.rl_phone);
        tvPhone = (TextView) findViewById(R.id.tv_phone);
        rlSex = (RelativeLayout) findViewById(R.id.rl_sex);
        tvSex = (TextView) findViewById(R.id.tv_sex);
        rlBirthday = (RelativeLayout) findViewById(R.id.rl_birthday);
        tvBirthday = (TextView) findViewById(R.id.tv_birthday);
        rlQuestion = (RelativeLayout) findViewById(R.id.rl_question);
        tvQuestion = (TextView) findViewById(R.id.tv_question);
        tvAnswer = (TextView) findViewById(R.id.tv_answer);
    }

    @Override
    protected void initViews() {
        super.initViews();
    }

    @Override
    protected void setupListeners() {
        super.setupListeners();

        rlUserHead.setOnClickListener(this);
        rlName.setOnClickListener(this);
        rlId.setOnClickListener(this);
        rlEmail.setOnClickListener(this);
        rlPhone.setOnClickListener(this);
        rlSex.setOnClickListener(this);
        rlBirthday.setOnClickListener(this);
        rlQuestion.setOnClickListener(this);


    }

    private void initToolBar() {
        setupToolbar(true, R.string.edit_user_data);
        setRightTextAndVisible(R.string.complete, View.VISIBLE);
        setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //完成
            }
        });
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.rl_user_head:

                break;

            case R.id.rl_name:

                break;
            case R.id.rl_id:

                break;
            case R.id.rl_email:

                break;
            case R.id.rl_phone:

                break;
            case R.id.rl_sex:

                break;
            case R.id.rl_birthday:

                break;
            case R.id.rl_question:

                break;




        }

    }


    /**
     * 显示pop
     */
    private void showLogout() {

        menuWindow = new SelectPicPopupWindow(EditUserDetailActivity.this, new View.OnClickListener() {

            public void onClick(View v) {
                menuWindow.dismiss();

                switch (v.getId()) {

                    case R.id.btn1:

                        break;
                    case R.id.btn2:

                        break;

                }


            }

        });

        menuWindow.setTextColor(getResources().getColor(R.color.text_dark_black), getResources().getColor(R.color.text_dark_black), getResources().getColor(R.color.text_dark_black));
        menuWindow.setText(R.string.take_photos, R.string.from_the_album_to_choose, R.string.cancel);

        //显示窗口
        menuWindow.showAtLocation(EditUserDetailActivity.this.findViewById(R.id.tv_logout), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置


    }


}
