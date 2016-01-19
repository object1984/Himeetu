package com.himeetu.ui.main;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;


import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.himeetu.R;
import com.himeetu.adapter.AdAdapter;
import com.himeetu.adapter.BaseAdapterHelper;
import com.himeetu.adapter.QuickAdapter;
import com.himeetu.adapter.RecommendAdapter;
import com.himeetu.app.Api;
import com.himeetu.app.Constants;
import com.himeetu.app.NavHelper;
import com.himeetu.model.Ad;
import com.himeetu.model.GsonResult;
import com.himeetu.model.HiEvent;
import com.himeetu.model.Recommend;
import com.himeetu.ui.base.BaseVolleyFragment;
import com.himeetu.util.JsonUtil;
import com.himeetu.view.ADViewPager;
import com.himeetu.view.AutoScaleImageView;
import com.himeetu.view.WrapContentLayoutManager;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseVolleyFragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private static final String TAG = HomeFragment.class.getCanonicalName();
    private static final String TAG_API_GET_TOP_RECOMMEND = "TAG_API_GET_TOP_RECOMMEND";
    private static final String TAG_API_GET_FRIEND_TALK= "TAG_API_GET_FRIEND_TALK";
    private static final String TAG_API_GET_AD= "TAG_API_GET_AD";
    private LayoutInflater inflater;

    private List<Recommend> recommendList = new ArrayList<>();

    private ListView mEventListView;
    private QuickAdapter<HiEvent> mEventAdapter;
    private RecyclerView mRecommendRecyclerView;
    private RecommendAdapter recommendAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    private ADViewPager adViewPager;
    private AdAdapter adAdapter;
    private List<View> adViewList = new ArrayList<>();
    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
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

        getAD();
        getTopRecommend();
        getFriendTalk();

        uploadState();
        return rootView;
    }

    @Override
    protected void loadViews() {
        super.loadViews();
        mEventListView = (ListView)rootView.findViewById(R.id.list_event);
        mEventListView.setOnItemClickListener(this);
         inflater = LayoutInflater.from(getActivity());

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

        adViewPager = (ADViewPager)rootView.findViewById(R.id.pager_ad);


        adAdapter = new AdAdapter(getActivity());
        adViewPager.setAdapter(adAdapter);
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

    private void getAD(){
        Api.getAD(TAG_API_GET_AD, this, this);
    }

    private int pageNum = 0;
    private int pageSize = 20;
    private void getFriendTalk(){
        Api.getFriendTalk(TAG_API_GET_FRIEND_TALK, pageNum*pageSize, pageSize, this, this);
    }

    private void uploadState(){
        Api.uploadState("TAG_UPLOAD_STATE", 1, this, this);
    }

    @Override
    public void onResponse(GsonResult response, String tag) {
        super.onResponse(response, tag);

        if(TAG_API_GET_TOP_RECOMMEND.equals(tag)){
            JSONObject jsonObject = JsonUtil.getJSONObject(response.getJsonStr());

            if(jsonObject == null){
                return;
            }

            Type listType = new TypeToken<List<Recommend>>() {
            }.getType();

            JSONArray  listJsonAry = JsonUtil.getJSONArray(jsonObject, "list");

            if(listJsonAry == null){
                return;
            }
            List<Recommend> recommends  = new Gson().fromJson(listJsonAry.toString(), listType);

            if(recommends != null){
                recommendAdapter.addAll(recommends);
            }
        }

        if(TAG_API_GET_FRIEND_TALK.equals(tag)){
//            JSONObject jsonObject = JsonUtil.getJSONObject(response.getJsonStr());
//
//            Type listType = new TypeToken<List<Recommend>>() {
//            }.getType();
//            List<Recommend> recommends  = new Gson().fromJson(JsonUtil.getJSONArray(jsonObject, "list").toString(), listType);
//
//            if(recommends != null){
//                recommendAdapter.addAll(recommends);
//            }
        }

        if(TAG_API_GET_AD.equals(tag)){
            JSONObject jsonObject = JsonUtil.getJSONObject(response.getJsonStr());

            if(jsonObject == null){
                return;
            }

            Type listType = new TypeToken<List<Ad>>() {
            }.getType();

            JSONArray  listJsonAry = JsonUtil.getJSONArray(jsonObject, "list");

            if(listJsonAry == null){
                return;
            }

            List<Ad> ads  = new Gson().fromJson(listJsonAry.toString(), listType);

            if(ads != null){
                for(int i=0; i<ads.size(); i++){
                    View adView = inflater.inflate(R.layout.item_ad, null);
                    AutoScaleImageView imageView = (AutoScaleImageView)adView.findViewById(R.id.img_ad);

                    if(TextUtils.isEmpty(ads.get(i).getImg())){
                        imageView.setImageResource(R.drawable.img_default);
                    }else{
                        String imgUrl = Constants.WEB_IMG_BASE + ads.get(i).getImg();
                        Picasso.with(getActivity()).load(imgUrl).placeholder(R.drawable.img_default)
                                .error(R.drawable.img_default).into(imageView);

                    }
                    adViewList.add(adView);
                }

                adAdapter.addAll(adViewList);
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error, String tag) {
        super.onErrorResponse(error, tag);
    }
}
