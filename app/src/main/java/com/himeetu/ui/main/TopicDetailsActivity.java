package com.himeetu.ui.main;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.github.siyamed.shapeimageview.RoundedImageView;
import com.himeetu.R;
import com.himeetu.app.Api;
import com.himeetu.model.GsonResult;
import com.himeetu.ui.base.BaseActivity;
import com.himeetu.ui.base.BaseVolleyActivity;
import com.himeetu.util.ToastUtil;

/**
 * Created by zhangshuaiqi on 2015/12/19.
 * 话题详情页
 */
public class TopicDetailsActivity extends BaseVolleyActivity implements ImageView.OnClickListener {
    private final String TAG_API_TOPICDETAILS = "TAG_API_TOPICDETAILS";//获取话题详情
    private final String TAG_API_TOPICDETAILS_FOLLOW = "TAG_API_TOPICDETAILS_FOLLOW";//话题详情关注
    private final String TAG_API_TOPICDETAILS_COMMENT = "TAG_API_TOPICDETAILS_COMMENT";//发表评论
    private RoundedImageView img_head_portrait;//用户头像
    private TextView tv_details_user_name;//用户名
    private TextView tv_details_publication_time;//发表时间
    private TextView tv_details_follow;//关注
    private ImageView img_details_content;//大图
    private TextView text_details_content;//内容
    private TextView tv_details_praise; //赞数量
    private EditText edit_send_comments;//评论内容
    private Button bnt_send_comments;//发表


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_topic);
        initViews();
    }

    /**
     * 初始化界面
     */
    @Override
    protected void initViews() {
        setupToolbar(true, R.string.home_detail_title);//设置标题栏

        img_head_portrait = (RoundedImageView) findViewById(R.id.img_head_portrait);
        tv_details_user_name = (TextView) findViewById(R.id.tv_details_user_name);
        tv_details_publication_time = (TextView) findViewById(R.id.tv_details_publication_time);
        tv_details_follow = (TextView) findViewById(R.id.tv_details_follow);
        img_details_content = (ImageView) findViewById(R.id.img_details_content);
        text_details_content = (TextView) findViewById(R.id.text_details_content);
        tv_details_praise = (TextView) findViewById(R.id.tv_details_praise);
        edit_send_comments = (EditText) findViewById(R.id.edit_send_comments);
        bnt_send_comments = (Button) findViewById(R.id.bnt_send_comments);

        tv_details_follow.setOnClickListener(this);
        bnt_send_comments.setOnClickListener(this);
        //设置赞心图标
        Drawable drawable = getResources().getDrawable(R.drawable.ic_home_zan);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());//必须设置图片大小，否则不显示
        tv_details_praise.setCompoundDrawablePadding(10);//设置text与drawableleft 间距
        tv_details_praise.setCompoundDrawables(drawable, null, null, null);

        //网络请求
        Api.getTopicDetails(TAG_API_TOPICDETAILS, 8, 0, 10, 0, this, this);
    }

    @Override
    public void onResponse(GsonResult response, String tag) {
        super.onResponse(response, tag);
        if (TAG_API_TOPICDETAILS.equals(tag)) {//获取详情
            int code = response.getCode();
            switch (code) {
                case 0:
                    ToastUtil.show("获取成功！");
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
        }else if(TAG_API_TOPICDETAILS_COMMENT.equals(tag)){//发表
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
        if (TAG_API_TOPICDETAILS.equals(tag)) {
            ToastUtil.show("获取失败！");
        } else if (TAG_API_TOPICDETAILS_FOLLOW.equals(tag)) {//关注
            ToastUtil.show("关注失败！");
        } else if(TAG_API_TOPICDETAILS_COMMENT.equals(tag)){//评论
            ToastUtil.show("评论失败！");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_details_follow://关注
                if (tv_details_follow.getText().toString().equals("关注"))
                    Api.addFriend(TAG_API_TOPICDETAILS_FOLLOW, 18, this, this);//uid = 18
                break;
            case R.id.bnt_send_comments://发表评论
                //加判空条件
                Api.commentNews(TAG_API_TOPICDETAILS_COMMENT,18,edit_send_comments.getText().toString().trim(),this,this);//tid = 18
                break;
        }

    }
}
