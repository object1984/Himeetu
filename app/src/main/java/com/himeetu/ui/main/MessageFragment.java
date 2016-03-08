package com.himeetu.ui.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.himeetu.R;
import com.himeetu.adapter.BaseAdapterHelper;
import com.himeetu.adapter.QuickAdapter;
import com.himeetu.app.Api;
import com.himeetu.model.GsonResult;
import com.himeetu.model.HiActivity;
import com.himeetu.model.Message;
import com.himeetu.ui.base.BaseVolleyFragment;
import com.himeetu.util.DateUtils;
import com.himeetu.util.JsonUtil;
import com.himeetu.util.RoundedTransformation;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MessageFragment extends BaseVolleyFragment implements View.OnClickListener, OnRefreshListener, OnLoadMoreListener {
    private static final String TAG = MessageFragment.class.getCanonicalName();
    private static final String TAG_API_GET_MESSAGE = "TAG_API_GET_MESSAGE";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ListView messageListView;
    private SwipeToLoadLayout swipeToLoadLayout;

    private QuickAdapter<Message>  messageAdapter;
    private List<Message> messageList = new ArrayList<>();


    public MessageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            setStatusBarColor(R.color.black);
        }
    }

    public static MessageFragment newInstance(String param1, String param2) {
        MessageFragment fragment = new MessageFragment();
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
        }
        setStatusBarColor(R.color.black);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       rootView = inflater.inflate(R.layout.fragment_message, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupToolbar(false, R.string.message);
        init();
        getMessage();
    }

    @Override
    protected void loadViews() {
        super.loadViews();
        messageListView = (ListView)rootView.findViewById(R.id.swipe_target);
        swipeToLoadLayout = (SwipeToLoadLayout)rootView.findViewById(R.id.swipeToLoadLayout);

        messageAdapter = new QuickAdapter<Message>(getActivity(), R.layout.item_list_message) {
            @Override
            protected void convert(BaseAdapterHelper helper, Message item) {
                helper.setText(R.id.text_name, item.getRolename());

                String msg = "";
                switch (item.getType()){
                    case 0:
                        msg = String.format("评论了你的图片");
                        break;
                    case 1:
                        msg = String.format("攒了你的图片");
                        break;
                    case 2:
                        msg = String.format("取消了你的攒");
                        break;
                    case 3:
                        msg = String.format("回复了你");
                        break;
                }
                helper.setText(R.id.text_message, msg);

                Date date = DateUtils.parse(item.getCtime());
                String timeStr = date.getTime() + "";
                helper.setText(R.id.text_time, DateUtils.getStandardDate(timeStr));
            }
        };

        messageListView.setAdapter(messageAdapter);
    }

    @Override
    protected void setupListeners() {
        super.setupListeners();
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
    }

    @Override
    protected void initViews() {
        super.initViews();

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

    private int pageNum = 0;
    private int pageSize = 20;
    private void getMessage(){
        Api.getMessage(TAG_API_GET_MESSAGE, pageNum*pageSize, pageSize, this, this);
    }

    @Override
    public void onResponse(GsonResult response, String tag) {
        super.onResponse(response, tag);

        if(TAG_API_GET_MESSAGE.equals(tag)){
            swipeToLoadLayout.setRefreshing(false);
            swipeToLoadLayout.setLoadingMore(false);

            JSONObject jsonObject = JsonUtil.getJSONObject(response.getJsonStr());

            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
            Gson gson = gsonBuilder.create();

            Type listType = new TypeToken<List<Message>>() {}.getType();
            List<Message> messages  = gson.fromJson(JsonUtil.getJSONArray(jsonObject, "list").toString(), listType);

            if(messages != null){
                if(swipeToLoadLayout.isRefreshing()){
                    messageAdapter.clear();
                    messageAdapter.addAll(messages);
                }else if(swipeToLoadLayout.isLoadingMore()){
                    messageAdapter.addAll(messages);
                }

            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error, String tag) {
        super.onErrorResponse(error, tag);
        swipeToLoadLayout.setRefreshing(false);
        swipeToLoadLayout.setLoadingMore(false);
    }


    @Override
    public void onRefresh() {
        getMessage();
    }

    @Override
    public void onLoadMore() {
        getMessage();

    }
}
