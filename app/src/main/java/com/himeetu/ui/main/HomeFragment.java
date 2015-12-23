package com.himeetu.ui.main;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;



import com.himeetu.R;
import com.himeetu.adapter.BaseAdapterHelper;
import com.himeetu.adapter.QuickAdapter;
import com.himeetu.adapter.RecommendAdapter;
import com.himeetu.app.NavHelper;
import com.himeetu.model.HiEvent;
import com.himeetu.model.Recommend;
import com.himeetu.ui.base.BaseFragment;
import com.himeetu.ui.base.StatusBarCompat;
import com.himeetu.view.WrapContentLayoutManager;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;


public class HomeFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private List<Integer> imageIdList;
    private List<Recommend> recommends = new ArrayList<>();

    private ListView mEventListView;
    private QuickAdapter<HiEvent> mEventAdapter;

    private PtrFrameLayout ptrFrameLayout;

    private RecyclerView mRecommendRecyclerView;
    private RecommendAdapter recommendAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        setStatusBarColor(R.color.app_default);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            setStatusBarColor(R.color.app_default);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       rootView = inflater.inflate(R.layout.fragment_home, container, false);

        init();
        return rootView;
    }

    @Override
    protected void loadViews() {
        super.loadViews();

        recommends.add(new Recommend("#精彩话题1"));
        recommends.add(new Recommend("#精彩话题2"));
        recommends.add(new Recommend("#精彩话题3"));
        recommends.add(new Recommend("#精彩话题4"));
        recommends.add(new Recommend("#精彩话题5"));
        recommends.add(new Recommend("#精彩话题6"));
        recommends.add(new Recommend("#精彩话题7"));
        recommends.add(new Recommend("#精彩话题8"));

        mEventListView = (ListView)rootView.findViewById(R.id.list_event);
        mEventListView.setOnItemClickListener(this);
        LayoutInflater inflater = LayoutInflater.from(getActivity());

        View headerView = inflater.inflate(R.layout.header_home, mEventListView, false);
        View footerView = inflater.inflate(R.layout.item_list_footer_common, mEventListView, false);
        mEventListView.addHeaderView(headerView);
        mEventListView.addFooterView(footerView);

        mRecommendRecyclerView = (RecyclerView)headerView.findViewById(R.id.recycler_recommend);
        recommendAdapter = new RecommendAdapter(getActivity(), recommends);

        mEventAdapter = new QuickAdapter<HiEvent>(getActivity(), R.layout.item_list_event) {
            @Override
            protected void convert(BaseAdapterHelper helper, HiEvent item) {
                helper.getView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NavHelper.toTalkDetailPage(getActivity());
                    }
                });
            }
        };

        WrapContentLayoutManager linearLayoutManager = new WrapContentLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        mRecommendRecyclerView.setHasFixedSize(true);
        mRecommendRecyclerView.setLayoutManager(linearLayoutManager);
        mRecommendRecyclerView.setAdapter(recommendAdapter);
        recommendAdapter.notifyDataSetChanged();
        mEventListView.setAdapter(mEventAdapter);

        mEventAdapter.add(new HiEvent());
        mEventAdapter.add(new HiEvent());
        mEventAdapter.add(new HiEvent());
        mEventAdapter.add(new HiEvent());
        mEventAdapter.add(new HiEvent());
        mEventAdapter.add(new HiEvent());
        mEventAdapter.add(new HiEvent());

        swipeRefreshLayout = (SwipeRefreshLayout)rootView.findViewById(R.id.layout_refresh);
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

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()){

        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        NavHelper.toTalkDetailPage(getActivity());
    }
}
