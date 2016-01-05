package com.himeetu.ui.main;

import android.app.Activity;
import android.graphics.Color;
import android.opengl.GLException;
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


import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.himeetu.R;
import com.himeetu.adapter.BaseAdapterHelper;
import com.himeetu.adapter.QuickAdapter;
import com.himeetu.adapter.RecommendAdapter;
import com.himeetu.app.Api;
import com.himeetu.app.NavHelper;
import com.himeetu.model.GsonResult;
import com.himeetu.model.HiEvent;
import com.himeetu.model.Recommend;
import com.himeetu.ui.base.BaseFragment;
import com.himeetu.ui.base.BaseVolleyActivity;
import com.himeetu.ui.base.BaseVolleyFragment;
import com.himeetu.ui.base.StatusBarCompat;
import com.himeetu.util.JsonUtil;
import com.himeetu.view.WrapContentLayoutManager;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;


public class HomeFragment extends BaseVolleyFragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private static final String TAG = HomeFragment.class.getCanonicalName();
    private static final String TAG_API_GET_TOP_RECOMMEND = "TAG_API_GET_TOP_RECOMMEND";


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private List<Integer> imageIdList;
    private List<Recommend> recommendList = new ArrayList<>();

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

        getTopRecommend();
        return rootView;
    }

    @Override
    protected void loadViews() {
        super.loadViews();


        mEventListView = (ListView)rootView.findViewById(R.id.list_event);
        mEventListView.setOnItemClickListener(this);
        LayoutInflater inflater = LayoutInflater.from(getActivity());

        View headerView = inflater.inflate(R.layout.header_home, mEventListView, false);
        View footerView = inflater.inflate(R.layout.item_list_footer_common, mEventListView, false);
        mEventListView.addHeaderView(headerView);
        mEventListView.addFooterView(footerView);

        mRecommendRecyclerView = (RecyclerView)headerView.findViewById(R.id.recycler_recommend);
        recommendAdapter = new RecommendAdapter(getActivity(), recommendList);

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
        rootView.findViewById(R.id.layout_search).setOnClickListener(this);
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
            case R.id.layout_search:
                toSearch();
                break;
        }
    }

    private void toSearch() {
        NavHelper.toSearchPage(getActivity());
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        NavHelper.toTalkDetailPage(getActivity());
    }

    private void getTopRecommend(){
        Api.getHotRecommend(TAG_API_GET_TOP_RECOMMEND, this, this);
    }

    @Override
    public void onResponse(GsonResult response, String tag) {
        super.onResponse(response, tag);

        if(TAG_API_GET_TOP_RECOMMEND.equals(tag)){
            JSONObject jsonObject = JsonUtil.getJSONObject(response.getJsonStr());

            Type listType = new TypeToken<List<Recommend>>() {
            }.getType();
            List<Recommend> recommends  = new Gson().fromJson(JsonUtil.getJSONArray(jsonObject, "list").toString(), listType);

            if(recommends != null){
                recommendAdapter.addAll(recommends);
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error, String tag) {
        super.onErrorResponse(error, tag);
    }
}
