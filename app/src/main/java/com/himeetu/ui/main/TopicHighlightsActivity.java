package com.himeetu.ui.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.himeetu.R;
import com.himeetu.adapter.BaseAdapterHelper;
import com.himeetu.adapter.QuickAdapter;
import com.himeetu.ui.base.BaseVolleyActivity;
import com.himeetu.util.ToastUtil;

/**
 * The official event topic highlights
 * Created by zhangshuaiqi on 2016/1/19.
 */
public class TopicHighlightsActivity extends BaseVolleyActivity {
    private ListView lv_topic_highlight;
    private SwipeRefreshLayout swipeRefreshLayout;//刷新
    private QuickAdapter<String> quickAdapter;

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
        setToolbarTitle("I Miss You 之夜");
        lv_topic_highlight = (ListView) findViewById(R.id.lv_topic_highlight);
        LayoutInflater inflater = LayoutInflater.from(this);
        View headerView = inflater.inflate(R.layout.item_list_header_topic_highlights, lv_topic_highlight, false);
        lv_topic_highlight.addHeaderView(headerView);

    }

    @Override
    protected void initViews() {
        super.initViews();

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
        //刷新
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.layout_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);

            }
        });
    }

    @Override
    protected void setupListeners() {
        super.setupListeners();
    }
}
