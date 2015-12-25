package com.himeetu.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.himeetu.R;
import com.himeetu.adapter.BaseAdapterHelper;
import com.himeetu.adapter.QuickAdapter;
import com.himeetu.model.SelectData;

import java.util.List;


public class SelectPicPopupWindow extends PopupWindow {


    private TextView btn_cancel;
    private View mMenuView;
    private NoScrollListview mListView;
    private Activity mContext;


    public SelectPicPopupWindow(Activity context, AdapterView.OnItemClickListener itemClickListener, List<SelectData> datas) {
        super(context);

        mContext = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.alert_dialog, null);
        mListView = (NoScrollListview) mMenuView.findViewById(R.id.list);
        btn_cancel = (TextView) mMenuView.findViewById(R.id.btn_cancel);
        //取消按钮
        btn_cancel.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                //销毁弹出框
                dismiss();
            }
        });

        initList(context,itemClickListener,datas);
        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(Color.parseColor("#99000000"));//百分之60的透明
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = mMenuView.findViewById(R.id.pop_layout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });

    }

    public void setCancelText(int text) {
        if (btn_cancel != null) {
            btn_cancel.setText(text);
        }

    }

    public void setCancelTextColor(int color) {
        if (btn_cancel != null) {
            btn_cancel.setTextColor(mContext.getResources().getColor(color));
        }
    }

    public void setCancelVisible(boolean b) {
        if (btn_cancel != null) {
            btn_cancel.setVisibility(b ? View.VISIBLE : View.GONE);
        }

    }


    private void initList(Activity context, AdapterView.OnItemClickListener itemClickListener,List<SelectData> datas) {

        mListView.setAdapter(new QuickAdapter<SelectData>(context, R.layout.item_select_pop,datas) {
            @Override
            protected void convert(BaseAdapterHelper helper, SelectData item) {

                helper.setText(R.id.btn, context.getString(item.getName()));
                helper.setTextColor(R.id.btn, context.getResources().getColor(item.getTextColor()));
                if(item.getTextSize() > 0){
                    helper.setTextSize(R.id.btn, item.getTextSize());
                }
            }
        });

        mListView.setOnItemClickListener(itemClickListener);

    }

}
