package com.himeetu.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.himeetu.R;

public class SelectPicPopupWindow extends PopupWindow {


    private TextView btn1, btn2, btn_cancel;
    private View mMenuView,line;

    public SelectPicPopupWindow(Activity context, OnClickListener itemsOnClick) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.alert_dialog, null);
        line = mMenuView.findViewById(R.id.line);
        btn1 = (TextView) mMenuView.findViewById(R.id.btn1);
        btn2 = (TextView) mMenuView.findViewById(R.id.btn2);
        btn_cancel = (TextView) mMenuView.findViewById(R.id.btn_cancel);
        //取消按钮
        btn_cancel.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                //销毁弹出框
                dismiss();
            }
        });
        //设置按钮监听
        btn1.setOnClickListener(itemsOnClick);
        btn2.setOnClickListener(itemsOnClick);
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

    public void setText(int text1, int text2, int text3) {
        if (btn1 != null) {
            btn1.setText(text1);
        }
        if (btn2 != null) {
            btn2.setText(text2);
        }
        if (btn_cancel != null) {
            btn_cancel.setText(text3);
        }

    }

    public void setTextColor(int color1, int color2, int color3) {
        if (btn1 != null) {
            btn1.setTextColor(color1);
        }
        if (btn2 != null) {
            btn2.setTextColor(color2);
        }
        if (btn_cancel != null) {
            btn_cancel.setTextColor(color3);
        }
    }

    public void setVisible(boolean b1, boolean b2, boolean b3) {
        if (btn1 != null) {
            btn1.setVisibility(b1 ? View.VISIBLE : View.GONE);
            line.setVisibility(b1 ? View.VISIBLE : View.GONE);
        }
        if (btn2 != null) {
            btn2.setVisibility(b2 ? View.VISIBLE : View.GONE);
        }
        if (btn_cancel != null) {
            btn_cancel.setVisibility(b3 ? View.VISIBLE : View.GONE);
        }

    }

}
