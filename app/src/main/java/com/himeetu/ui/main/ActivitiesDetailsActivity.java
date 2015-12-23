package com.himeetu.ui.main;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.himeetu.R;
import com.himeetu.adapter.DetailsRecyclerAdapter;
import com.himeetu.app.NavHelper;
import com.himeetu.ui.base.BaseActivity;
import com.himeetu.util.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangshuaiqi on 2015/12/19.
 * 活动详情页
 */
public class ActivitiesDetailsActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout toolbar_details;//标题栏
    private TextView joinTextView;//我要参加
    private RecyclerView recycler_details;
    private DetailsRecyclerAdapter recyclerAdapter;
    private List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setThemeTranslucent();
        setContentView(R.layout.activity_details_activies);
        setupToolbar(true,R.string.home_detail_title);//设置标题栏
        init();
    }

    @Override
    protected void loadViews() {
        super.loadViews();
        joinTextView = (TextView) findViewById(R.id.text_join);
        toolbar_details = (RelativeLayout) findViewById(R.id.toolbar);
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
        recyclerAdapter = new DetailsRecyclerAdapter(this,mList);
        recycler_details.setAdapter(recyclerAdapter);
        //添加分割线
        recycler_details.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL_LIST));
        addListData();

        //等接口数据
        setToolbarTitle("I Miss You 之夜");
    }

    @Override
    protected void setupListeners() {
        super.setupListeners();

        findViewById(R.id.toolbar_share).setOnClickListener(this);
    }

    //测试添加数据
    private void addListData(){
        for (int i = 0;i < 10;i++){
            mList.add("text"+i);
        }
        recyclerAdapter.notifyDataSetChanged();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_share:
                toShare();
                break;
        }
    }

    private void toShare() {
        NavHelper.toSharePage(this);
    }
}
