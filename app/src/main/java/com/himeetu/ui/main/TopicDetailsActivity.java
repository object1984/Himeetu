package com.himeetu.ui.main;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.siyamed.shapeimageview.RoundedImageView;
import com.himeetu.R;
import com.himeetu.ui.base.BaseActivity;

/**
 * Created by zhangshuaiqi on 2015/12/19.
 * 话题详情页
 */
public class TopicDetailsActivity extends BaseActivity {
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
        setContentView(R.layout.activity_main_details);
        initViews();
    }

    /**
     * 初始化界面
     */
    @Override
    protected void initViews() {
        setupToolbar(true,R.string.home_detail_title);//设置标题栏

        img_head_portrait = (RoundedImageView) findViewById(R.id.img_head_portrait);
        tv_details_user_name = (TextView) findViewById(R.id.tv_details_user_name);
        tv_details_publication_time = (TextView) findViewById(R.id.tv_details_publication_time);
        tv_details_follow = (TextView) findViewById(R.id.tv_details_follow);
        img_details_content = (ImageView) findViewById(R.id.img_details_content);
        text_details_content = (TextView) findViewById(R.id.text_details_content);
        tv_details_praise = (TextView) findViewById(R.id.tv_details_praise);
        edit_send_comments = (EditText) findViewById(R.id.edit_send_comments);
        bnt_send_comments = (Button) findViewById(R.id.bnt_send_comments);
       //设置赞心图标
        Drawable drawable = getResources().getDrawable(R.drawable.ic_home_zan);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());//必须设置图片大小，否则不显示
        tv_details_praise.setCompoundDrawablePadding(10);//设置text与drawableleft 间距
        tv_details_praise.setCompoundDrawables(drawable, null, null, null);

    }
}