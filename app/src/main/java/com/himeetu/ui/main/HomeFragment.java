package com.himeetu.ui.main;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.TextView;


import com.android.volley.VolleyError;
import com.github.siyamed.shapeimageview.RoundedImageView;
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
import com.himeetu.model.Recommend;
import com.himeetu.model.Talk;
import com.himeetu.model.Word;
import com.himeetu.ui.base.BaseVolleyFragment;
import com.himeetu.ui.base.CommonWebActivity;
import com.himeetu.util.DensityUtil;
import com.himeetu.util.JsonUtil;
import com.himeetu.util.RoundedTransformation;
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
    private List<Talk> talkList = new ArrayList<>();

    private ListView mTalkListView;
    private QuickAdapter<Talk> mTalkAdapter;
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
        mTalkListView = (ListView)rootView.findViewById(R.id.list_event);
        mTalkListView.setOnItemClickListener(this);
         inflater = LayoutInflater.from(getActivity());

        View headerView = inflater.inflate(R.layout.header_home, mTalkListView, false);
        View footerView = inflater.inflate(R.layout.item_list_footer_common, mTalkListView, false);

        mTalkListView.addHeaderView(headerView);
        mTalkListView.addFooterView(footerView);

        mRecommendRecyclerView = (RecyclerView)headerView.findViewById(R.id.recycler_recommend);
        recommendAdapter = new RecommendAdapter(getActivity(), recommendList);

        mTalkAdapter = new QuickAdapter<Talk>(getActivity(), R.layout.item_list_talk) {
            @Override
            protected void convert(final BaseAdapterHelper helper, final Talk talk) {

                helper.setText(R.id.text_name, talk.getRolename());
                helper.setText(R.id.text_zan_num, String.format("%d 赞", talk.getLikenum()));


                RoundedImageView headImageView = (RoundedImageView)helper.getView().findViewById(R.id.img_head);
                ImageView countryImageView = (ImageView)helper.getView().findViewById(R.id.img_country);
                ImageView talkImageView = (ImageView)helper.getView().findViewById(R.id.img_talk);

                if(talk.getPortrait() != null){
                    Picasso.with(context).load(talk.getPortrait()).placeholder(R.drawable.img_avatar_default)
                            .error(R.drawable.img_avatar_default).transform(new RoundedTransformation(100, 0)).fit().into(headImageView);
                }
                if(talk.getImgPath() != null){
                    Picasso.with(context).load(talk.getImgPath().replace("sysimg", "img")).placeholder(R.drawable.img_default)
                            .error(R.drawable.img_default).into(talkImageView);
                }

                helper.getView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        NavHelper.toTalkDetailPage(getActivity());
                    }
                });



                if(TextUtils.isEmpty(talk.getDes())){
                    helper.setVisible(R.id.text_desc, false);
                }else {
                    helper.setText(R.id.text_desc, talk.getDes());
                    helper.setVisible(R.id.text_desc, true);
                }

                List<View> wordViews = new ArrayList<>();
                wordViews.add(helper.getView().findViewById(R.id.reply_1));
                wordViews.add(helper.getView().findViewById(R.id.reply_2));
                wordViews.add(helper.getView().findViewById(R.id.reply_3));

                for(int i=0; i< talk.getWordsList().size(); i++){
                    Word word = talk.getWordsList().get(i);
                    TextView wordView = (TextView) wordViews.get(i);
                    wordView.setText(String.format("%s: %s", word.getRolename(), word.getWords()));
                    wordView.setVisibility(View.VISIBLE);
                }

                if(talk.getWordsTotal() > 2){
                    helper.setText(R.id.reply_more, String.format("显示全部%d条评论", talk.getWordsTotal()));
                    helper.setVisible(R.id.reply_more, true);
                }else {
                    helper.setVisible(R.id.reply_more, false);
                }
            }
        };

        WrapContentLayoutManager linearLayoutManager = new WrapContentLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        mRecommendRecyclerView.setHasFixedSize(true);
        mRecommendRecyclerView.setLayoutManager(linearLayoutManager);
        mRecommendRecyclerView.setAdapter(recommendAdapter);
        recommendAdapter.notifyDataSetChanged();
        mTalkListView.setAdapter(mTalkAdapter);

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
            JSONObject jsonObject = JsonUtil.getJSONObject(response.getJsonStr());

            Type listType = new TypeToken<List<Talk>>() {
            }.getType();
            List<Talk> talks  = new Gson().fromJson(JsonUtil.getJSONArray(jsonObject, "list").toString(), listType);

            if(talks != null) {
                mTalkAdapter.addAll(talks);
            }
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
                    final Ad ad = ads.get(i);
                    View adView = inflater.inflate(R.layout.item_ad, null);
                    AutoScaleImageView imageView = (AutoScaleImageView)adView.findViewById(R.id.img_ad);

                    if(TextUtils.isEmpty(ads.get(i).getImg())){
                        imageView.setImageResource(R.drawable.img_default);
                    }else{
                        String imgUrl = Constants.WEB_IMG_BASE + ads.get(i).getImg();
                        Picasso.with(getActivity()).load(imgUrl).placeholder(R.drawable.img_default)
                                .error(R.drawable.img_default).into(imageView);

                    }
                    adView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.putExtra("url", ad.getUrl());
                            intent.putExtra("title", ad.getWords());
                            intent.setClass(getContext(), CommonWebActivity.class);
                            startActivity(intent);
                        }
                    });
                    adViewList.add(adView);
                }

                adAdapter.addAll(adViewList);
            }
        }
    }

    private void renderTalkView(){

    }

    @Override
    public void onErrorResponse(VolleyError error, String tag) {
        super.onErrorResponse(error, tag);
    }
}
