package com.himeetu.ui.setup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.himeetu.BuildConfig;
import com.himeetu.R;
import com.himeetu.app.Api;
import com.himeetu.model.GsonResult;
import com.himeetu.model.SelectData;
import com.himeetu.model.User;
import com.himeetu.model.service.UserService;
import com.himeetu.ui.base.BaseActivity;
import com.himeetu.ui.base.BaseVolleyActivity;
import com.himeetu.util.FileUtil;
import com.himeetu.util.ToastUtil;
import com.himeetu.view.SelectPicPopupWindow;

import android.os.Bundle;
import android.app.Activity;
import android.widget.RelativeLayout;

import com.github.siyamed.shapeimageview.RoundedImageView;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 编辑用户资料   id   email  不能修改
 */
public class EditUserDetailActivity extends BaseVolleyActivity implements View.OnClickListener {
    private SelectPicPopupWindow menuWindow;
    private RelativeLayout rlUserHead;
    private RoundedImageView rivUserHead, riv_user_head_edit;
    private RelativeLayout rlName;
    private TextView tvName;
    private RelativeLayout rlPhone;
    private TextView tvPhone;
    private RelativeLayout rlSex;
    private TextView tvSex;
    private RelativeLayout rlBirthday;
    private TextView tvBirthday;
    private TextView tvId;
    private TextView tvEmail;
    //    private RelativeLayout rlQuestion;
//    private TextView tvQuestion;
//    private TextView tvAnswer;
    private LinearLayout llShow, llEdit;
    private EditText et_name, et_email, et_phone, et_sex, et_birthday, et_id;
    //    private EditText et_question,et_answer;
    private TextView tv_head_edit;
    private static final String TAG_UPDATE_DATA_DETAIL = "TAG_UPDATE_DATA_DETAIL";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_detail);
        super.init();
        initToolBar();

//        testUpload();
    }


    private void testUpload() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                File file = new File("/mnt/sdcard/abc.png");

//                FileUtil.uploadFile("http://123.57.167.135/sys/uploadimg","abc.png",file,"image/png");
                try {

                    String result = FileUtil.run("http://123.57.167.135/sys/uploadimg", "image/png", file, "abc.png");

                    Log.d("lanzhihong", "result===" + result);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        ;


    }


    @Override
    protected void loadViews() {
        super.loadViews();

        rlUserHead = (RelativeLayout) findViewById(R.id.rl_user_head);
        rivUserHead = (RoundedImageView) findViewById(R.id.riv_user_head);
        rlName = (RelativeLayout) findViewById(R.id.rl_name);
        tvName = (TextView) findViewById(R.id.tv_name);
        rlPhone = (RelativeLayout) findViewById(R.id.rl_phone);
        tvPhone = (TextView) findViewById(R.id.tv_phone);
        rlSex = (RelativeLayout) findViewById(R.id.rl_sex);
        tvSex = (TextView) findViewById(R.id.tv_sex);
        tvId = (TextView) findViewById(R.id.tv_id);
        tvEmail = (TextView) findViewById(R.id.tv_email);
        rlBirthday = (RelativeLayout) findViewById(R.id.rl_birthday);
        tvBirthday = (TextView) findViewById(R.id.tv_birthday);
//        rlQuestion = (RelativeLayout) findViewById(R.id.rl_question);
//        tvQuestion = (TextView) findViewById(R.id.tv_question);
//        tvAnswer = (TextView) findViewById(R.id.tv_answer);
        riv_user_head_edit = (RoundedImageView) findViewById(R.id.riv_user_head_edit);
        llShow = (LinearLayout) findViewById(R.id.ll_show);
        llEdit = (LinearLayout) findViewById(R.id.ll_edit);
        tv_head_edit = (TextView) findViewById(R.id.tv_head_edit);
        et_name = (EditText) findViewById(R.id.et_name);
        et_email = (EditText) findViewById(R.id.et_email);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_sex = (EditText) findViewById(R.id.et_sex);
        et_birthday = (EditText) findViewById(R.id.et_birthday);
//        et_question = (EditText) findViewById(R.id.et_question);
//        et_answer = (EditText) findViewById(R.id.et_answer);
        et_id = (EditText) findViewById(R.id.et_id);

    }

    @Override
    protected void initViews() {
        super.initViews();

        User user = UserService.get();

        et_id.setText(user.getUid() + "");
        et_name.setText(user.getNickname());
        et_birthday.setText(user.getBirthday());
        et_sex.setText("1".equals(user.getSex()) ? "女" : "男");
        et_email.setText(user.getEmail());
        et_phone.setText(user.getTelPhone());

    }

    @Override
    protected void setupListeners() {
        super.setupListeners();

//        rlUserHead.setOnClickListener(this);
//        rlName.setOnClickListener(this);
//        rlPhone.setOnClickListener(this);
//        rlSex.setOnClickListener(this);
//        rlBirthday.setOnClickListener(this);
//        rlQuestion.setOnClickListener(this);
        tv_head_edit.setOnClickListener(this);
        riv_user_head_edit.setOnClickListener(this);


    }

    private void initToolBar() {
        setupToolbar(true, R.string.edit_user_data);
        setRightTextAndVisible(R.string.complete, View.VISIBLE);

        setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = getRightText();

                if (getString(R.string.edit).equals(text)) {//完成

                    setRightTextAndVisible(R.string.complete, View.VISIBLE);
                    llShow.setVisibility(View.GONE);
                    llEdit.setVisibility(View.VISIBLE);
                } else {//编辑
                    setRightTextAndVisible(R.string.edit, View.VISIBLE);
                    llShow.setVisibility(View.VISIBLE);
                    llEdit.setVisibility(View.GONE);

                    buildData();
                }
            }
        });
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

//            case R.id.rl_user_head:
//
//                break;
//
//            case R.id.rl_name:
//
//                break;
//            case R.id.rl_phone:
//
//                break;
//            case R.id.rl_sex:
//
//                break;
//            case R.id.rl_birthday:
//
//                break;
//            case R.id.rl_question:
//
//                break;


            case R.id.riv_user_head_edit:
                showSelectHeadView();
                break;

            case R.id.tv_head_edit:
                showSelectHeadView();

                break;

        }

    }


    /**
     * 显示pop
     */
    private void showSelectHeadView() {
        List<SelectData> datas = new ArrayList<>();
        SelectData data = new SelectData();
        data.setName(R.string.take_photos);
        data.setTextColor(R.color.text_dark_black);
        datas.add(data);

        data = new SelectData();
        data.setName(R.string.from_the_album_to_choose);
        data.setTextColor(R.color.text_dark_black);
        datas.add(data);

        menuWindow = new SelectPicPopupWindow(EditUserDetailActivity.this, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            }
        }, datas);

        //显示窗口
        menuWindow.showAtLocation(EditUserDetailActivity.this.findViewById(R.id.tv_head_edit), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置


    }

    private void buildData() {

//        if (isNull(et_name) || isNull(et_phone) || isNull(et_sex) || isNull(et_birthday)) {
//                || isNull(et_question) || isNull(et_answer)){

//            ToastUtil.show("资料填写不合格");
//            return;
//        }

        String sex = "男".equals(et_sex.getText().toString()) ? "0" : "1";

        commit(UserService.get().getCountryCode() + "", sex, et_birthday.getText().toString(), et_phone.getText().toString(), et_email.getText().toString());

    }

    private void refreshView() {

        tvName.setText(et_name.getText());
        tvPhone.setText(et_phone.getText());
        tvSex.setText("1".equals(et_sex.getText()) ? "女" : "男");
        tvBirthday.setText(et_birthday.getText());
        tvId.setText(et_id.getText());
        tvEmail.setText(et_email.getText());

    }

    private boolean isNull(EditText et) {
        if (et == null || isNull(et.getText().toString())) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isNull(String str) {
        if (str == null || str.trim().length() < 1) {
            return true;
        } else {
            return false;
        }
    }

    private void commit(String nation, String sex, String birth, String phone, String email) {

        Api.updateUserDataDetail(TAG_UPDATE_DATA_DETAIL, nation, sex, birth, phone, email, this, this);

    }

    @Override
    public void onResponse(GsonResult response, String tag) {
        super.onResponse(response, tag);
        if (BuildConfig.DEBUG) Log.d("EditUserDetailActivity", response.getJsonStr());
        if (TAG_UPDATE_DATA_DETAIL.equals(tag)) {

            if (response.getCode() == 0) {
                ToastUtil.show(R.string.success);
                refreshView();
            } else {

                ToastUtil.show(response.getMsg());
            }


        }
    }

    @Override
    public void onErrorResponse(VolleyError error, String tag) {
        super.onErrorResponse(error, tag);
    }
}
