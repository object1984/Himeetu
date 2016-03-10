package com.himeetu.ui.main;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.github.siyamed.shapeimageview.RoundedImageView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.himeetu.R;
import com.himeetu.adapter.BaseAdapterHelper;
import com.himeetu.adapter.QuickAdapter;
import com.himeetu.app.Api;
import com.himeetu.app.NavHelper;
import com.himeetu.model.GsonResult;
import com.himeetu.model.Talk;
import com.himeetu.model.TopicComments;
import com.himeetu.network.dic.Argument;
import com.himeetu.ui.base.BaseVolleyActivity;
import com.himeetu.util.DateUtils;
import com.himeetu.util.JsonUtil;
import com.himeetu.util.RoundedTransformation;
import com.himeetu.util.ToastUtil;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangshuaiqi on 2015/12/19.
 * 话题详情页
 */
public class TopicDetailsActivity extends BaseVolleyActivity implements View.OnClickListener, AdapterView.OnItemClickListener,OnRefreshListener, OnLoadMoreListener {
    private final String TAG_API_TOPICDETAILS = "TAG_API_TOPICDETAILS";//获取话题详情
    private final String TAG_API_TOPICDETAILS_FOLLOW = "TAG_API_TOPICDETAILS_FOLLOW";//话题详情关注
    private final String TAG_API_TOPICDETAILS_COMMENT = "TAG_API_TOPICDETAILS_COMMENT";//发表评论(二级评论)
    private final String TAG_API_COMMENT_PICTURE = "TAG_API_COMMENT_PICTURE";//评论用户的图片
    private RoundedImageView img_head_portrait;//用户头像
    private TextView tv_details_user_name;//用户名
    private TextView tv_details_publication_time;//发表时间
    private TextView tv_details_follow;//关注
    private ImageView img_details_content;//大图
    private TextView text_details_content;//内容
    private TextView tv_details_praise; //赞数量
    private EditText edit_send_comments;//评论内容
    private Button bnt_send_comments;//发表
    private SwipeToLoadLayout swipeToLoadLayout; //刷新
    private ListView lv_details_topic;//列表
    private QuickAdapter<TopicComments.TopicCommentsItem> quickAdapter;
    private int pageSize = 10;//每页要展示条数
    private int pageIndex = 1;//当前页码
    private int pageTotal = 1;//总页数
    private Talk talk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_topic);
        setStatusBarColor(R.color.black);
        init();
    }

    @Override
    protected void loadViews() {
        super.loadViews();
        setupToolbar(true, 0);
        lv_details_topic = (ListView) findViewById(R.id.swipe_target);
        LayoutInflater inflater = LayoutInflater.from(this);
        View headerView = inflater.inflate(R.layout.item_list_header_topic, lv_details_topic, false);
        lv_details_topic.addHeaderView(headerView);
        img_head_portrait = (RoundedImageView) headerView.findViewById(R.id.img_head_portrait);
        tv_details_user_name = (TextView) headerView.findViewById(R.id.tv_details_user_name);
        tv_details_publication_time = (TextView) headerView.findViewById(R.id.tv_details_publication_time);
        tv_details_follow = (TextView) headerView.findViewById(R.id.tv_details_follow);
        img_details_content = (ImageView) headerView.findViewById(R.id.img_details_content);
        text_details_content = (TextView) headerView.findViewById(R.id.text_details_content);
        tv_details_praise = (TextView) headerView.findViewById(R.id.tv_details_praise);
        edit_send_comments = (EditText) findViewById(R.id.edit_send_comments);
        bnt_send_comments = (Button) findViewById(R.id.bnt_send_comments);
        swipeToLoadLayout = (SwipeToLoadLayout) findViewById(R.id.swipeToLoadLayout);
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
    }

    /**
     * 初始化界面
     */
    @Override
    protected void initViews() {
        talk = (Talk) getIntent().getSerializableExtra(Argument.TALK);
        tv_details_user_name.setText(talk.getRolename());//用户名
        tv_details_praise.setText(String.format("%d 赞", talk.getLikenum()));//赞数量
        //发表时间
        Date date = DateUtils.parse(talk.getCtime());
        String timeStr = date.getTime() + "";
        tv_details_publication_time.setText(DateUtils.getStandardDate(timeStr));
        text_details_content.setText(URLDecoder.decode(talk.getDes()));//话题内容
        if (talk.getPortrait() != null) { //头像
            Picasso.with(this).load(talk.getPortrait()).placeholder(R.drawable.img_avatar_default)
                    .error(R.drawable.img_avatar_default).transform(new RoundedTransformation(100, 0)).fit().into(img_head_portrait);
        }
        if (talk.getImgPath() != null) { //大图
            Picasso.with(this).load(talk.getImgPath().replace("sysimg", "img")).placeholder(R.drawable.img_default)
                    .error(R.drawable.img_default).into(img_details_content);
        }

        img_details_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHelper.toImageShowActivity(TopicDetailsActivity.this, talk.getImgPath());
            }});
        quickAdapter = new QuickAdapter<TopicComments.TopicCommentsItem>(this, R.layout.item_list_details_topic) {
            @Override
            protected void convert(BaseAdapterHelper helper, TopicComments.TopicCommentsItem item) {
                if (helper.getPosition() == 0) {//顶部分隔线
                    helper.setVisible(R.id.line_topic_top, true);
                } else {
                    helper.setVisible(R.id.line_topic_top, false);
                }
                if (item.getWords() != null)
                    helper.setText(R.id.item_tv_comments_content, URLDecoder.decode(item.getWords()));//内容
                if (item.getCtime() != null)
                    helper.setText(R.id.item_tv_comments_time, item.getCtime());//时间
                //没有头像链接 & 用户名
            }
        };
        lv_details_topic.setAdapter(quickAdapter);
        //设置赞心图标
        Drawable drawable = getResources().getDrawable(R.drawable.ic_home_zan);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());//必须设置图片大小，否则不显示
        tv_details_praise.setCompoundDrawablePadding(10);//设置text与drawableleft 间距
        tv_details_praise.setCompoundDrawables(drawable, null, null, null);

        getCommentList();
    }

    private void getCommentList() {
        //网络请求---评论列表
        Api.getTopicDetails(TAG_API_TOPICDETAILS, Integer.parseInt(talk.getImgid()), pageIndex, pageSize, this, this);
    }

    @Override
    protected void setupListeners() {
        super.setupListeners();
        lv_details_topic.setOnItemClickListener(this);
        tv_details_follow.setOnClickListener(this);
        bnt_send_comments.setOnClickListener(this);
    }

    @Override
    public void onResponse(GsonResult response, String tag) {
        super.onResponse(response, tag);
        //停止下拉刷新
        swipeToLoadLayout.setRefreshing(false);
        swipeToLoadLayout.setLoadingMore(false);
        if (TAG_API_TOPICDETAILS.equals(tag)) {//获取评论列表
            int code = response.getCode();
            switch (code) {
                case 0:
                    try {
                        JSONObject jsonObject = JsonUtil.getJSONObject(response.getJsonStr());
                        Type listType = new TypeToken<List<TopicComments.TopicCommentsItem>>() {
                        }.getType();
                        List<TopicComments.TopicCommentsItem> usersList = new Gson().fromJson(JsonUtil.getJSONArray(jsonObject, "list").toString(), listType);
                        pageTotal = Integer.parseInt(jsonObject.getString("count")) / pageSize +
                                (Integer.parseInt(jsonObject.getString("count")) % pageSize > 0 ? 1 : 0);
                        if (usersList != null) {
                            quickAdapter.addAll(usersList);
                        }
                        ToastUtil.show("获取成功！");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;

            }
        } else if (TAG_API_TOPICDETAILS_FOLLOW.equals(tag)) {//关注
            int code = response.getCode();
            switch (code) {
                case 0:
                    ToastUtil.show("关注成功！");
                    tv_details_follow.setText("已关注");
                    break;
            }
        } else if (TAG_API_TOPICDETAILS_COMMENT.equals(tag)) {//二级发表
            int code = response.getCode();
            switch (code) {
                case 0:
                    ToastUtil.show("发表成功！");
                    edit_send_comments.setText("");//清空
                    break;
            }
        } else if (TAG_API_COMMENT_PICTURE.equals(tag)) { //一级评论
            int code = response.getCode();
            switch (code) {
                case 0:
                    ToastUtil.show("发表成功！");
                    edit_send_comments.setText("");//清空
                    break;
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error, String tag) {
        super.onErrorResponse(error, tag);
        swipeToLoadLayout.setRefreshing(false);
        swipeToLoadLayout.setLoadingMore(false);//停止刷新
        if (TAG_API_TOPICDETAILS.equals(tag)) {
            ToastUtil.show("获取失败！");
        } else if (TAG_API_TOPICDETAILS_FOLLOW.equals(tag)) {//关注
            ToastUtil.show("关注失败！");
        } else if (TAG_API_TOPICDETAILS_COMMENT.equals(tag)) {//二级评论
            ToastUtil.show("评论失败！");
        } else if (TAG_API_COMMENT_PICTURE.equals(tag)) {//一级评论
            ToastUtil.show("评论失败！");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_details_follow://关注
                if (tv_details_follow.getText().toString().equals("关注"))
                    Api.addFriends(TAG_API_TOPICDETAILS_FOLLOW, 18 + "", this, this);//uid = 18
                break;
            case R.id.bnt_send_comments://发表评论
                //加判空条件
                Api.commentPicture(TAG_API_COMMENT_PICTURE, talk.getImgid(), edit_send_comments.getText().toString().trim(), this, this);
//                Api.commentNews(TAG_API_TOPICDETAILS_COMMENT, 8, edit_send_comments.getText().toString().trim(), this, this);//tid = 18
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onLoadMore() {
        if (pageTotal > pageIndex) {//加载下一页
            pageIndex++;
            getCommentList();
        } else {
            swipeToLoadLayout.setLoadingMore(false);//停止刷新
            ToastUtil.show("没有更多！");
        }
    }

    @Override
    public void onRefresh() {
        pageIndex = 1;
        quickAdapter.clear();//清空列表
        getCommentList();
    }
}
