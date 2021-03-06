package com.himeetu.ui.main;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.himeetu.R;
import com.himeetu.adapter.DetailsRecyclerAdapter;
import com.himeetu.app.Api;
import com.himeetu.app.NavHelper;
import com.himeetu.model.GsonResult;
import com.himeetu.model.HiActivity;
import com.himeetu.model.User;
import com.himeetu.model.service.Activitys;
import com.himeetu.model.service.UserService;
import com.himeetu.network.dic.Argument;
import com.himeetu.ui.base.BaseVolleyActivity;
import com.himeetu.util.DateUtils;
import com.himeetu.util.DividerItemDecoration;
import com.himeetu.util.JsonUtil;
import com.himeetu.util.ToastUtil;
import com.himeetu.view.AutoScaleImageView;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangshuaiqi on 2015/12/19.
 * 活动详情页
 */
public class ActivitiesDetailsActivity extends BaseVolleyActivity implements View.OnClickListener {
    private static final String TAG_PARTICIPATE_IN_ACTIVE_USERS = "participate_in_active_users";
    private RelativeLayout toolbar_details;//标题栏
    private Button joinTextView;//我要参加
    private TextView tv_active_details_content;//详情
    private TextView text_time;//开始时间
    private TextView text_date;//开始日期
    private TextView text_address;//地址
    private RecyclerView recycler_details;
    private DetailsRecyclerAdapter recyclerAdapter;
    private AutoScaleImageView autoScaleImageView;
    private List<User> mList;
    private HiActivity hiActivity;
    private String imgPath;
    private int start = 0;
    private int limit = 30;
    private static final String TAG_JOIN_IN_THE_ACTIVITY = "TAG_JOIN_IN_THE_ACTIVITY";
    private final String TAG_GET_SELF = "TAG_GET_SELF";
    private static final String TAG_UPDATE_DATA_DETAIL = "TAG_UPDATE_DATA_DETAIL";
    private boolean IsJoin = false;
    private ActiveDialog activeDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_activies);
        setStatusBarColor(R.color.black);
        setupToolbar(true, R.string.home_detail_title);//设置标题栏
        init();
    }

    @Override
    protected void loadViews() {
        super.loadViews();
        joinTextView = (Button) findViewById(R.id.text_join);
        toolbar_details = (RelativeLayout) findViewById(R.id.toolbar);
        autoScaleImageView = (AutoScaleImageView) findViewById(R.id.img_details_content);
        text_time = (TextView) findViewById(R.id.text_time);
        text_date = (TextView) findViewById(R.id.text_date);
        text_address = (TextView) findViewById(R.id.text_address);
        tv_active_details_content = (TextView) findViewById(R.id.tv_active_details_content);
        recycler_details = (RecyclerView) findViewById(R.id.recycler_details);
        recycler_details.setFocusable(false);
    }


    @Override
    protected void initViews() {
        mList = new ArrayList<>();
        //ListView效果的 LinearLayoutManager
        LinearLayoutManager mgr = new LinearLayoutManager(this);
        mgr.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler_details.setLayoutManager(mgr);
        recyclerAdapter = new DetailsRecyclerAdapter(this, mList);
        recycler_details.setAdapter(recyclerAdapter);
        //添加分割线
        recycler_details.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL_LIST));
        //获取对象
        hiActivity = (HiActivity) getIntent().getSerializableExtra(Argument.HIACTIVITY);
        //等接口数据
        Api.getParticipateInActiveUsers(TAG_PARTICIPATE_IN_ACTIVE_USERS, hiActivity.getId(), start, limit, this, this);//id = 2
        setToolbarTitle(hiActivity.getName());//设置标题
        //获取图片链接
        imgPath = hiActivity.getImgPath();
        Picasso.with(this).load(imgPath).placeholder(R.drawable.img_default)
                .error(R.drawable.img_default).into(autoScaleImageView);
        //设置详情内容
        tv_active_details_content.setText(hiActivity.getDescription());
        //地址
        text_address.setText(hiActivity.getAddress());
        //设置时间
        String localeDate = DateUtils.LocaleDateFormat(hiActivity.getStartDate());//获取yyyy-MM-dd HH:mm:ss
        String[] timeAry = localeDate.split(" ");
        if (localeDate != null && !localeDate.equals("")) {
            text_time.setText(DateUtils.getAMorPM(hiActivity.getStartDate()) + timeAry[1]);
            //日期
            text_date.setText(timeAry[0]);
        }
    }

    @Override
    protected void setupListeners() {
        super.setupListeners();

        findViewById(R.id.toolbar_share).setOnClickListener(this);
        joinTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.toolbar_share:
                toShare();
                break;
            case R.id.text_join://我要参加

                User user = UserService.get();
                String phone = user.getTelphone();
                if (TextUtils.isEmpty(phone)) {

                    activeDialog = new ActiveDialog(this, IsJoin);
                    activeDialog.show();

                } else {
                    joinTheActivity(hiActivity.getId() + "");

                }
                break;
        }
    }

    @Override
    public void onResponse(GsonResult response, String tag) {
        super.onResponse(response, tag);
        if (TAG_PARTICIPATE_IN_ACTIVE_USERS.equals(tag)) {//获取参加用户列表
            int code = response.getCode();
            switch (code) {
                case 0:
                    JSONObject jsonObject = JsonUtil.getJSONObject(response.getJsonStr());
                    Type listType = new TypeToken<List<User>>() {
                    }.getType();
                    List<User> usersList = new Gson().fromJson(JsonUtil.getJSONArray(jsonObject, "list").toString(), listType);
                    if (usersList != null) {
                        recyclerAdapter.mList.addAll(usersList);
                        recyclerAdapter.notifyDataSetChanged();
                    }
                    ToastUtil.show("获取成功！");
                    break;

            }
        } else if (TAG_JOIN_IN_THE_ACTIVITY.equals(tag)) {  //用户参加活动
            int code = response.getCode();
            switch (code) {
                case 0:
                    ToastUtil.show("成功！");
                    break;
                case 1:
                    ToastUtil.show(response.getMsg());
                    break;
            }
        } else if (TAG_UPDATE_DATA_DETAIL.equals(tag)) {
            if (response.getCode() == 0) {

                User user = UserService.get();
                user.setTelphone(activeDialog.getPhone());
                UserService.save(user);

                joinTheActivity(hiActivity.getId() + "");
            } else {
                ToastUtil.show(response.getMsg());
            }

        }
    }

    @Override
    public void onErrorResponse(VolleyError error, String tag) {
        super.onErrorResponse(error, tag);
        if (TAG_PARTICIPATE_IN_ACTIVE_USERS.equals(tag)) {//获取参加用户列表
            ToastUtil.show("获取失败！");
        }
    }

    private void toShare() {
        NavHelper.toSharePage(this, null);
    }


    /**
     * 用户参与活动
     *
     * @param activityId
     */
    private void joinTheActivity(String activityId) {
        Api.joinInTheActivities(TAG_JOIN_IN_THE_ACTIVITY, activityId, ActivitiesDetailsActivity.this, ActivitiesDetailsActivity.this);
    }

    /**
     * 提交用户信息
     *
     * @param nation
     * @param sex
     * @param birth
     * @param phone
     * @param email
     */
    private void commit(String nation, String sex, String birth, String phone, String email) {

        Api.updateUserDataDetail(TAG_UPDATE_DATA_DETAIL, nation, sex, birth, phone, email, this, this);

    }


    /**
     * 活动dialog
     */
    private class ActiveDialog extends Dialog {
        private Context context;
        private boolean IsJoin;
        private EditText userPhone;

        public ActiveDialog(Context context, boolean IsJoin) {
            super(context);
            this.context = context;
            this.IsJoin = IsJoin;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);//去标题
            setContentView(R.layout.dialog_active_user_info);

            Window dialogWindow = getWindow();
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
            lp.width = (int) (d.widthPixels * 0.8); // 宽度设置为屏幕的0.8
            lp.height = (int) (d.heightPixels * 0.6); // 高度设置为屏幕的0.6
            dialogWindow.setAttributes(lp);

            Button ok = (Button) findViewById(R.id.dialog_bnt_ok);
            EditText dialog_edit_user_name = (EditText) findViewById(R.id.dialog_edit_user_name);
            dialog_edit_user_name.setText(UserService.get().getNickname());
            dialog_edit_user_name.setEnabled(false);
            userPhone = (EditText) findViewById(R.id.dialog_edit_user_phone);
            ok.setEnabled(!IsJoin); //设置是否可以点击
            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String phone = getPhone();
                    if (TextUtils.isEmpty(phone)) {
                        ToastUtil.show("请输入手机号");
                    } else {
                        commit("", "", "", phone, "");
                    }

                    dismiss();
                }
            });
            findViewById(R.id.dialog_bnt_close).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });

        }

        public String getPhone() {
            return userPhone.getText().toString();
        }
    }

}
