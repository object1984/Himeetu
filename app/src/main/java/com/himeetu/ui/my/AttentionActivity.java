package com.himeetu.ui.my;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.google.gson.Gson;
import com.himeetu.R;
import com.himeetu.adapter.BaseAdapterHelper;
import com.himeetu.adapter.QuickAdapter;
import com.himeetu.app.Api;
import com.himeetu.app.Constants;
import com.himeetu.model.Friend;
import com.himeetu.model.GsonResult;
import com.himeetu.model.service.UserService;
import com.himeetu.network.dic.Argument;
import com.himeetu.ui.base.BaseVolleyActivity;
import com.himeetu.ui.main.MeFragment;
import com.himeetu.util.RoundedTransformation;
import com.himeetu.util.ToastUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * 关注/粉丝 页面    获取好友列表 ＝＝ 我的关注
 * 个人动态  11  获取全部好友最新发表的图片
 */
public class AttentionActivity extends BaseVolleyActivity implements OnRefreshListener, OnLoadMoreListener {
    private MeFragment.AttentionType type;
    private ListView mListView;
    private final String TAG_GET_FRIENDS_LIST = "TAG_GET_FRIENDS_LIST";
    public static final String TAG_ADD_FRIEND = "TAG_ADD_FRIEND";
    private final String TAG_DEL_FRIEND = "TAG_DEL_FRIEND";
    private final String TAG_GET_FANS_LIST = "TAG_GET_FANS_LIST";
    private QuickAdapter adapter;
    private List<Friend.list> friends = new ArrayList<>();
    private int start_fans = 1, start_attention = 1;
    private int limit_fans = 15, limit_attention = 15;
    private SwipeToLoadLayout swipeToLoadLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention);

        type = (MeFragment.AttentionType) getIntent().getSerializableExtra(Argument.ATTENTION_TYPE);

        initToolBar();

        init();

        onRefresh();

//        addFriend("12");
//        addFriend("14");
//        addFriend("15");

    }


    @Override
    protected void loadViews() {
        super.loadViews();
        mListView = (ListView) findViewById(R.id.swipe_target);

        swipeToLoadLayout = (SwipeToLoadLayout) findViewById(R.id.swipeToLoadLayout);

        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
    }

    @Override
    protected void initViews() {
        super.initViews();
    }

    @Override
    protected void setupListeners() {
        super.setupListeners();
    }

    @Override
    public void onErrorResponse(VolleyError error, String tag) {
        super.onErrorResponse(error, tag);
    }

    @Override
    public void onResponse(GsonResult response, String tag) {
        super.onResponse(response, tag);
        swipeToLoadLayout.setRefreshing(false);
        swipeToLoadLayout.setLoadingMore(false);
        if (tag == TAG_GET_FRIENDS_LIST || tag == TAG_GET_FANS_LIST) {  //我的关注

            Friend friend = new Gson().fromJson(response.getJsonStr(), Friend.class);

            if (friend == null || friend.getList() == null || friend.getList().size() == 0) {
                ToastUtil.show(R.string.not_more);
            }

            if (type == MeFragment.AttentionType.ATTENTION.FANS) {
                if (start_fans == 0) {  //  ==0 表示 刷新
                    if (friends != null) {
                        friends.clear();
                    }
                }
            } else if (type == MeFragment.AttentionType.ATTENTION.ATTENTION) {

                if (start_attention == 0) {  //  ==0 表示 刷新
                    if (friends != null) {
                        friends.clear();
                    }
                }
            }

            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }

            friends.addAll(friend.getList());
            setListData();

        } else if (AttentionActivity.TAG_ADD_FRIEND.equals(tag) || TAG_DEL_FRIEND.equals(tag)) {

            if (response.getCode() == 0) {

                ToastUtil.show(R.string.success);

                onRefresh();
            } else {
                if (response == null || TextUtils.isEmpty(response.getMsg())) {
                    return;
                }
                ToastUtil.show(response.getMsg());
            }

        }

    }

    private void initToolBar() {
        int title = 0;
        if (type == MeFragment.AttentionType.ATTENTION.FANS) {
            title = R.string.consider_me;
        } else if (type == MeFragment.AttentionType.ATTENTION.ATTENTION) {
            title = R.string.me_consider;
        }

        setupToolbar(true, title);
        setToolBarColor(getResources().getColor(R.color.white));
    }


    private void getFriendsList() {
        Api.getFriendsList(TAG_GET_FRIENDS_LIST, start_attention, limit_attention, UserService.get().getUid(), this, this);
    }

    private void getFansList() {
        Api.getFansList(TAG_GET_FANS_LIST, UserService.get().getUid() + "", start_fans, limit_fans, this, this);
    }

    private void addFriend(String friendId) {

        Api.addFriends(TAG_ADD_FRIEND, friendId, this, this);
    }

    private void delFriend(String friendId) {
        Api.delFriends(TAG_DEL_FRIEND, friendId, this, this);
    }


    private void setListData() {
        adapter = new QuickAdapter<Friend.list>(AttentionActivity.this, R.layout.item_list_attention, friends) {
            @Override
            protected void convert(final BaseAdapterHelper helper, Friend.list item) {

                helper.setText(R.id.tv_name, item.getRolename());

                helper.setImageResource(R.id.im_head, R.drawable.image1);

                ImageView headImageView = helper.getView(R.id.im_head);

                Picasso.with(context).load(Constants.HEAD_IMG_BASE + item.getPortrait()).placeholder(R.drawable.img_avatar_default)
                        .error(R.drawable.img_avatar_default).transform(new RoundedTransformation(100, 0)).fit().into(headImageView);

                final int position = helper.getPosition();

                if (type == MeFragment.AttentionType.ATTENTION.FANS) {

                    helper.setText(R.id.bt_attention, "关注");
                } else if (type == MeFragment.AttentionType.ATTENTION.ATTENTION) {

                    helper.setText(R.id.bt_attention, "取消");
                }

                helper.setOnClickListener(R.id.bt_attention, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (position >= friends.size()) {
                            return;
                        }

                        if (type == MeFragment.AttentionType.ATTENTION.FANS) {
                            String uid = friends.get(position).getUid() + "";
                            addFriend(uid);
                        } else if (type == MeFragment.AttentionType.ATTENTION.ATTENTION) {
                            String uid = friends.get(position).getFriend() + "";
                            delFriend(uid);
                        }
                    }
                });
            }
        };

        mListView.setAdapter(adapter);


    }

    @Override
    public void onLoadMore() {
        if (type == MeFragment.AttentionType.ATTENTION.FANS) {
            start_fans++;
            getFansList();
        } else if (type == MeFragment.AttentionType.ATTENTION.ATTENTION) {
            start_attention++;
            getFriendsList();
        }
    }

    @Override
    public void onRefresh() {
        if (type == MeFragment.AttentionType.ATTENTION.FANS) {
            start_fans = 0;
            getFansList();
        } else if (type == MeFragment.AttentionType.ATTENTION.ATTENTION) {
            start_attention = 0;
            getFriendsList();
        }
    }
}
