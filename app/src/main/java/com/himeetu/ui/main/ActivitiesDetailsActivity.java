package com.himeetu.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.himeetu.R;
import com.himeetu.adapter.DetailsRecyclerAdapter;
import com.himeetu.app.Api;
import com.himeetu.app.Constants;
import com.himeetu.app.NavHelper;
import com.himeetu.model.ActiveUsers;
import com.himeetu.model.GsonResult;
import com.himeetu.model.HiActivity;
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
import java.util.Date;
import java.util.List;

/**
 * Created by zhangshuaiqi on 2015/12/19.
 * 活动详情页
 */
public class ActivitiesDetailsActivity extends BaseVolleyActivity implements View.OnClickListener {
    private static final String TAG_PARTICIPATE_IN_ACTIVE_USERS = "participate_in_active_users";
    private RelativeLayout toolbar_details;//标题栏
    private TextView joinTextView;//我要参加
    private TextView tv_active_details_content;//详情
    private TextView text_time;//开始时间
    private TextView text_date;//开始日期
    private TextView text_address;//地址
    private RecyclerView recycler_details;
    private DetailsRecyclerAdapter recyclerAdapter;
    private AutoScaleImageView autoScaleImageView;
    private List<ActiveUsers> mList;
    private HiActivity hiActivity;
    private String imgPath;
    private int start = 1;
    private int limit = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setThemeTranslucent();
        setContentView(R.layout.activity_details_activies);
        setupToolbar(true, R.string.home_detail_title);//设置标题栏
        init();
    }

    @Override
    protected void loadViews() {
        super.loadViews();
        joinTextView = (TextView) findViewById(R.id.text_join);
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.toolbar_share:
                toShare();
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
                    Type listType = new TypeToken<List<ActiveUsers>>() {
                    }.getType();
                    List<ActiveUsers> usersList = new Gson().fromJson(JsonUtil.getJSONArray(jsonObject, "list").toString(), listType);
                    if (usersList != null) {
                        recyclerAdapter.mList.addAll(usersList);
                        recyclerAdapter.notifyDataSetChanged();
                    }
                    ToastUtil.show("获取成功！");
                    break;

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
}
