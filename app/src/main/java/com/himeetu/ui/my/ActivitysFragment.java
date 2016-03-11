package com.himeetu.ui.my;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.google.gson.Gson;
import com.himeetu.BuildConfig;
import com.himeetu.R;
import com.himeetu.adapter.ActivitysAdapter;
import com.himeetu.app.Api;
import com.himeetu.model.FriendImgs;
import com.himeetu.model.FriendsImages;
import com.himeetu.model.GsonResult;
import com.himeetu.model.ListItem;
import com.himeetu.model.User;
import com.himeetu.model.UserImg;
import com.himeetu.model.service.Activitys;
import com.himeetu.ui.base.BaseFragment;
import com.himeetu.ui.base.BaseVolleyFragment;
import com.himeetu.util.DateUtils;
import com.himeetu.util.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;

/**
 * 我的界面 活动list
 * A fragment representing a list of Items.
 * <p>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ActivitysFragment extends BaseVolleyFragment implements OnRefreshListener, OnLoadMoreListener {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mType;
    public static final int TYPE_GRDT = 2;
    public static final int TYPE_CYHD = 3;
    public static final int TYPE_QBDT = 4;
    private static final String TYPE = "type";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private FamiliarRecyclerView recyclerView;
    private final String TAG_GET_SELF = "TAG_GET_SELF";
    private final String TAG_GET_FRIENDS_IMG = "TAG_GET_FRIENDS_IMG";
    private int start_self = 0, start_friendimg = 0;
    private int limit_self = 10, limit__friendimg = 10;
    private List<ListItem> lists = new ArrayList<>();
    private List<FriendImgs.FriendImg> imgs = new ArrayList<>();
    private ActivitysAdapter adapter;
    private SwipeToLoadLayout swipeToLoadLayout;

    private View emptyView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ActivitysFragment() {

    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ActivitysFragment newInstance(int columnCount, int type) {
        ActivitysFragment fragment = new ActivitysFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putInt(TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            mType = getArguments().getInt(TYPE);
        }

        onRefresh();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_list_activitys, container, false);
        emptyView = inflater.inflate(R.layout.layout_empty_common, null);

        recyclerView = (FamiliarRecyclerView) view.findViewById(R.id.swipe_target);
        swipeToLoadLayout = (SwipeToLoadLayout) view.findViewById(R.id.swipeToLoadLayout);

        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);

        // Set the adapter
        Context context = view.getContext();
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        adapter = new ActivitysAdapter(getActivity(), lists);
        recyclerView.setEmptyView(emptyView);
        recyclerView.setAdapter(adapter);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onLoadMore() {
        switch (mType) {

            case TYPE_CYHD:

                start_self+=limit_self;
                getSelf();

                break;

            case TYPE_GRDT:
                start_friendimg+=limit__friendimg;
                getFriendsImg();

                break;

        }
    }

    @Override
    public void onRefresh() {
        switch (mType) {

            case TYPE_CYHD:

                start_self = 0;
                getSelf();

                break;

            case TYPE_GRDT:
                start_friendimg = 0;
                getFriendsImg();

                break;

        }
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(ListItem item);
    }

    /**
     * 参与的活动
     */
    private void getSelf() {
        Api.getSelf(TAG_GET_SELF, start_self, limit_self, this, this);
    }

    private void getFriendsImg() {
        Api.getFriendsImg(TAG_GET_FRIENDS_IMG, start_friendimg, limit__friendimg, this, this);
    }

    @Override
    public void onResponse(GsonResult response, String tag) {
        super.onResponse(response, tag);
        swipeToLoadLayout.setRefreshing(false);
        swipeToLoadLayout.setLoadingMore(false);

        if (BuildConfig.DEBUG)
            Log.d("ActivitysFragment", "TAG_GET_SELF===response:" + response.getJsonStr());

        if (TAG_GET_SELF.equals(tag)) {
            if (response.getCode() == 0) {

                //                    Activitys activitys = new Gson().fromJson(response.getJsonStr(), Activitys.class);
                Activitys activitys = new Gson().fromJson(response.getJsonStr(), Activitys.class);

                List<Activitys.Activity> activityList = activitys.getActivitys();

                if (activityList != null && activityList.size() > 0) {
                    if (start_self == 0) {  //  ==0 表示 刷新
                        lists.clear();
                    }
                }

                for (Activitys.Activity activity : activityList) {
                    ListItem item = new ListItem();

                    if (!TextUtils.isEmpty(activity.getImg())) {
                        item.setImgPath(activity.getImg());
                    }

                    item.setTime(activity.getStarttime());
                    //                                + "--" + activity.getEndtime());
                    lists.add(item);
                }

                adapter.notifyDataSetChanged();


            } else {

                ToastUtil.show(response.getMsg());
            }
        }


        if (TAG_GET_FRIENDS_IMG.equals(tag)) {

            if (BuildConfig.DEBUG) Log.d("ActivitysFragment", "response:" + response.getJsonStr());
            if (response.getCode() == 0) {

                FriendsImages friendsImages = new Gson().fromJson(response.getJsonStr(), FriendsImages.class);

                if (friendsImages == null || friendsImages.getList() == null) {
                    return;
                }

                List<FriendsImages.ListEntity> images = friendsImages.getList();

                if (start_friendimg == 0) {  //  ==0 表示 刷新
                    lists.clear();
                }

                for (FriendsImages.ListEntity image : images) {

                    ListItem item = new ListItem();

                    if (!TextUtils.isEmpty(image.getImg_path())) {
                        item.setImgPath(image.getImg_path());
                    }

                    item.setTime(image.getCtime());
                    lists.add(item);
                }

//            FriendImgs friends = new Gson().fromJson(response.getJsonStr(), FriendImgs.class);
//            if(friends == null || friends.getFriendImgs() == null){
//                return;
//            }
//
//            imgs.addAll(friends.getFriendImgs());
//
//            for (FriendImgs.FriendImg img : imgs) {
//                ListItem item = new ListItem();
//
//                if(!TextUtils.isEmpty(img.getImg_path())){
//                    String imgPath = img.getImg_path().replace("sysimg", "img");
//                    item.setImgPath(imgPath);
//                }
//
//                item.setTime(img.getCtime());
//                lists.add(item);
//            }
                adapter.notifyDataSetChanged();
            } else {

                ToastUtil.show(response.getMsg());
            }
        }

    }

    @Override
    public void onErrorResponse(VolleyError error, String tag) {
        super.onErrorResponse(error, tag);
    }
}
