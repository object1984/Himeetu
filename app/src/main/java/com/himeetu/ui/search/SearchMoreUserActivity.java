package com.himeetu.ui.search;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AbsListView;
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
public class SearchMoreUserActivity extends BaseVolleyActivity implements  AbsListView.OnScrollListener {
    private static final String TAG_API_SEARCH_USER = "TAG_API_SEARCH_USER";

    private ListView mListView;
    private QuickAdapter mAdapter;
    private List<User> mData = new ArrayList<>();
    private int start = 0;
    private int limit = 20;
    private int currentPage = 0;

    private boolean loadingMore = true;
    int current_status 		= 1;
    //get more status
    final int MORE_STATUS_HID			= -1,
    MORE_STATUS_ERR 		= 0,
    MORE_STATUS_GET 		= 1,
    MORE_STATUS_END 		= 2,
    MORE_STATUS_EMP 		= 3;
    private View footerView;
    private  String param;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_more);
        setStatusBarColor(R.color.black);
        setupToolbar(true, R.string.search);
        init();

         param = getIntent().getStringExtra("user");

        searchUser(currentPage);
    }

    @Override
    protected void loadViews() {
        super.loadViews();
        footerView = View.inflate(this, R.layout.item_list_more, null);

        mListView = (ListView)findViewById(R.id.list_search);
        mListView.addFooterView(footerView);
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

    @Override
    protected void setupListeners() {
        super.setupListeners();

        mListView.setOnScrollListener(this);
    }

    private void searchUser(int page){
        Api.searchUser(TAG_API_SEARCH_USER, param, page * limit, limit, this, this);
    }

    @Override
    public void onResponse(GsonResult response, String tag) {
        super.onResponse(response, tag);

        if(TAG_API_SEARCH_USER.equals(tag)){
            currentPage ++;

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

                if(users.size() ==  0){
                    setGetMoreStatus(MORE_STATUS_EMP);
                }else if(users.size() < limit){
                    setGetMoreStatus(MORE_STATUS_END);
                }
            }else {
                setGetMoreStatus(MORE_STATUS_EMP);
            }
        }
    }

    //获取更多评论
    public void loadMore( boolean isReTry ){
        loadingMore = true;
        searchUser(currentPage++);
    }

    public void setGetMoreStatus(int status){

        String msg = "";
        current_status = status;

        switch(status){
            case MORE_STATUS_HID:
                footerView.setVisibility(View.GONE);
            case MORE_STATUS_GET:
                loadingMore = false;
                msg = getString(R.string.loading_more);
                break;
            case MORE_STATUS_END:
                loadingMore = true;
                msg = getString(R.string.no_more);
                break;
            case MORE_STATUS_EMP:
                loadingMore = true;
                msg = getString(R.string.content_empty);
                break;
            case MORE_STATUS_ERR:
                loadingMore = true;
                msg = getString(R.string.get_more_when_lose);

        }
        ((TextView) footerView).setText(msg);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if(totalItemCount == firstVisibleItem + visibleItemCount){
//            loadingMore = true;
//            loadMore(true);
        }
    }
}
