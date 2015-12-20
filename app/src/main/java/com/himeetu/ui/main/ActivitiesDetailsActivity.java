package com.himeetu.ui.main;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.himeetu.R;
import com.himeetu.adapter.DetailsRecyclerAdapter;
import com.himeetu.ui.base.BaseActivity;
import com.himeetu.util.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangshuaiqi on 2015/12/19.
 * 活动详情页
 */
public class ActivitiesDetailsActivity extends BaseActivity {
    private ScrollView scroll_details;
    private RelativeLayout toolbar_details;//标题栏
    private LinearLayout ll_details_join;//我要参加
    private RecyclerView recycler_details;
    private DetailsRecyclerAdapter recyclerAdapter;
    private List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_activies);
        initViews();
    }

    @Override
    protected void initViews() {
        setupToolbar(true,R.string.home_detail_title);//设置标题栏

        toolbar_details = (RelativeLayout) findViewById(R.id.toolbar_details);
        scroll_details = (ScrollView) findViewById(R.id.scroll_details);
        ll_details_join = (LinearLayout) findViewById(R.id.ll_details_join);
        recycler_details = (RecyclerView) findViewById(R.id.recycler_details);
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

        //设置scrollView的 layout_marginBottom
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.topMargin = getViewHeight(toolbar_details);
        lp.bottomMargin = getViewHeight(ll_details_join);
        scroll_details.setLayoutParams(lp);
    }
    //测试添加数据
    private void addListData(){
        for (int i = 0;i < 10;i++){
            mList.add("text"+i);
        }
        recyclerAdapter.notifyDataSetChanged();
    }

    /**
     * 获取控件的高度
     * @param view
     * @return
     */
    private int getViewHeight(View view){
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        int height = view.getMeasuredHeight();
        return height;
    }


}
