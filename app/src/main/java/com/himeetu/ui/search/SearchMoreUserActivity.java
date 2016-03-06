package com.himeetu.ui.search;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.himeetu.R;
import com.himeetu.adapter.BaseAdapterHelper;
import com.himeetu.adapter.QuickAdapter;
import com.himeetu.app.Api;
import com.himeetu.app.NavHelper;
import com.himeetu.model.Friend;
import com.himeetu.model.GsonResult;
import com.himeetu.model.User;
import com.himeetu.ui.base.BaseActivity;
import com.himeetu.ui.base.BaseVolleyActivity;
import com.himeetu.util.JsonUtil;
import com.himeetu.util.RoundedTransformation;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 *查找更多用户
 */
public class SearchMoreUserActivity extends BaseVolleyActivity {
    private static final String TAG_API_SEARCH_USER = "TAG_API_SEARCH_USER";

    private ListView mListView;
    private QuickAdapter mAdapter;
    private List<User> mData = new ArrayList<>();
    private int start = 0;
    private int limit = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_more);
        setStatusBarColor(R.color.black);
        setupToolbar(true, R.string.search);
        init();

        String param = getIntent().getStringExtra("user");

        searchUser(param);
    }

    @Override
    protected void loadViews() {
        super.loadViews();

        mListView = (ListView)findViewById(R.id.list_search);
        mAdapter = new QuickAdapter<User>(this, R.layout.item_search_user) {
            @Override
            protected void convert(BaseAdapterHelper helper, User user) {
                helper.setText(R.id.text_nickname, user.getNickname());

                ImageView userHeadImageView = (ImageView) helper.getView(R.id.img_user);

                Picasso.with(SearchMoreUserActivity.this).load(user.getPortrait()).placeholder(R.drawable.img_avatar_default)
                        .error(R.drawable.img_avatar_default).transform(new RoundedTransformation(100, 0)).fit().into(userHeadImageView);
            }
        };

        mListView.setAdapter(mAdapter);
    }

    private void searchUser(String user){
        Api.searchUser(TAG_API_SEARCH_USER, user, 0, 3, this, this);
    }

    @Override
    public void onResponse(GsonResult response, String tag) {
        super.onResponse(response, tag);

        if(TAG_API_SEARCH_USER.equals(tag)){
            JSONObject jsonObject = JsonUtil.getJSONObject(response.getJsonStr());

            if(jsonObject == null){
                return;
            }

            Type listType = new TypeToken<List<User>>() {
            }.getType();

            JSONArray listJsonAry = JsonUtil.getJSONArray(jsonObject, "list");

            if(listJsonAry == null){
                return;
            }
            List<User> users  = new Gson().fromJson(listJsonAry.toString(), listType);

            if(users != null){
                mAdapter.addAll(users);
            }
        }
    }
}
