package com.himeetu.ui.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.himeetu.R;
import com.himeetu.adapter.BaseAdapterHelper;
import com.himeetu.adapter.QuickAdapter;
import com.himeetu.model.HiActivity;
import com.himeetu.network.dic.Argument;
import com.himeetu.ui.base.BaseVolleyActivity;
import com.himeetu.util.DateUtils;
import com.himeetu.util.LogUtil;
import com.himeetu.util.ToastUtil;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

/**
 * The official event topic highlights
 * Created by zhangshuaiqi on 2016/1/19.
 */
public class TopicHighlightsActivity extends BaseVolleyActivity implements OnRefreshListener, OnLoadMoreListener {
    private ListView lv_topic_highlight;
    private SwipeToLoadLayout swipeToLoadLayout; //刷新
    private QuickAdapter<String> quickAdapter;
    private HiActivity hiActivitys;
    private TextView item_header_tv_hint;
    private TextView tv_head_topic_highlights_time;
    private TextView tv_head_topic_highlights_address;
    private TextView tv_head_topic_highlights_date;
    private ImageView img_head_topic_highlights;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_highlights);
        setStatusBarColor(R.color.black);
        init();
    }

    @Override
    protected void loadViews() {
        super.loadViews();
        setupToolbar(true, 0);
        lv_topic_highlight = (ListView) findViewById(R.id.swipe_target);
        LayoutInflater inflater = LayoutInflater.from(this);
        View headerView = inflater.inflate(R.layout.item_list_header_topic_highlights, lv_topic_highlight, false);
        lv_topic_highlight.addHeaderView(headerView);
        item_header_tv_hint = (TextView) headerView.findViewById(R.id.item_header_tv_hint);
        tv_head_topic_highlights_time = (TextView) headerView.findViewById(R.id.tv_head_topic_highlights_time);
        tv_head_topic_highlights_address = (TextView) headerView.findViewById(R.id.tv_head_topic_highlights_address);
        tv_head_topic_highlights_date = (TextView) headerView.findViewById(R.id.tv_head_topic_highlights_date);
        img_head_topic_highlights = (ImageView) headerView.findViewById(R.id.img_head_topic_highlights);
        swipeToLoadLayout = (SwipeToLoadLayout) findViewById(R.id.swipeToLoadLayout);

    }

    @Override
    protected void initViews() {
        super.initViews();
        hiActivitys = (HiActivity) getIntent().getSerializableExtra(Argument.HIACTIVITY);
        setToolbarTitle(hiActivitys.getName());//标题
        //赋值
        item_header_tv_hint.setText(hiActivitys.getName());
        Calendar cal = Calendar.getInstance();
        cal.setTime(hiActivitys.getStartDate());
        String time = "";
        if (cal.get(Calendar.AM_PM) == Calendar.PM) {
            time += "PM: ";
        }else {
            time += "AM: ";
        }
        time += String.format("%s", DateUtils.formatTime(hiActivitys.getStartDate()));
        tv_head_topic_highlights_time.setText(time);
        tv_head_topic_highlights_address.setText("地址：" + hiActivitys.getAddress());
        String date = String.format("%s",DateUtils.formatYear(hiActivitys.getStartDate()));
        tv_head_topic_highlights_date.setText(date);
        Picasso.with(this).load(hiActivitys.getImgPath()).placeholder(R.drawable.img_default)
                .error(R.drawable.img_default).into(img_head_topic_highlights);

        quickAdapter = new QuickAdapter<String>(this,R.layout.item_list_topic_highlights) {
            @Override
            protected void convert(BaseAdapterHelper helper, String item) {
                if (helper.getPosition() == 0) {//顶部分隔线
                    helper.setVisible(R.id.line_topic_highlights_top, true);
                } else {
                    helper.setVisible(R.id.line_topic_highlights_top, false);
                }
            }
        };
        quickAdapter.add(new String());
        quickAdapter.add(new String());
        quickAdapter.add(new String());
        quickAdapter.add(new String());
        quickAdapter.add(new String());
        quickAdapter.add(new String());
        lv_topic_highlight.setAdapter(quickAdapter);
        lv_topic_highlight.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    // 当不滚动时
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        // 判断滚动到底部
                        if (lv_topic_highlight.getLastVisiblePosition() == (lv_topic_highlight.getCount() - 1)) {
                            ToastUtil.show("没有更多~");
                        }
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    @Override
    protected void setupListeners() {
        super.setupListeners();
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
    }

    @Override
    public void onLoadMore() {
        //停止刷新
        swipeToLoadLayout.setLoadingMore(false);

    }

    @Override
    public void onRefresh() {
        swipeToLoadLayout.setRefreshing(false);

    }
}
