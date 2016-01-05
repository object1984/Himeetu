package com.himeetu.ui.main;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.himeetu.R;
import com.himeetu.adapter.BaseAdapterHelper;
import com.himeetu.adapter.QuickAdapter;
import com.himeetu.app.Api;
import com.himeetu.model.GsonResult;
import com.himeetu.model.HiActivity;
import com.himeetu.model.UserImg;
import com.himeetu.network.volley.PersistentCookieStore;
import com.himeetu.ui.base.BaseFragment;
import com.himeetu.ui.base.BaseVolleyFragment;
import com.himeetu.ui.base.StatusBarCompat;
import com.himeetu.util.JsonUtil;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class MessageFragment extends BaseVolleyFragment implements View.OnClickListener{
    private static final String TAG = MessageFragment.class.getCanonicalName();
    private static final String TAG_API_GET_MESSAGE = "TAG_API_GET_MESSAGE";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private ListView messageListView;
    private SwipeRefreshLayout messageRefreshLayout;
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setStatusBarColor(R.color.black);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       rootView = inflater.inflate(R.layout.fragment_message, container, false);
        setupToolbar(false, R.string.message);
        init();
        getMessage();
        return rootView;
    }

    @Override
    protected void loadViews() {
        super.loadViews();
        messageListView = (ListView)rootView.findViewById(R.id.list_message);
        messageRefreshLayout = (SwipeRefreshLayout)rootView.findViewById(R.id.refresh_message);

        messageAdapter = new QuickAdapter<Message>(getActivity(), R.layout.item_list_message) {
            @Override
            protected void convert(BaseAdapterHelper helper, Message item) {

            }
        };

        messageListView.setAdapter(messageAdapter);
    }

    @Override
    protected void setupListeners() {
        super.setupListeners();

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
            JSONObject jsonObject = JsonUtil.getJSONObject(response.getJsonStr());

            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
            Gson gson = gsonBuilder.create();

            Type listType = new TypeToken<List<HiActivity>>() {}.getType();
            List<Message> messages  = gson.fromJson(JsonUtil.getJSONArray(jsonObject, "list").toString(), listType);

            if(messages != null){
                messageAdapter.addAll(messages);
                messageAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error, String tag) {
        super.onErrorResponse(error, tag);
    }


}
