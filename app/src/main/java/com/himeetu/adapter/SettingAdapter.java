package com.himeetu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by object1984 on 15/11/22.
 */
//public class SettingAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {
//    private final Context mContext;
//    private final LayoutInflater mLayoutInflater;
//    private List<SettingItem> mSettingItems = new ArrayList<SettingItem>();
//
//
//    public SettingAdapter(Context context, List<SettingItem> settingItemList) {
//        this.mContext = context;
//        this.mLayoutInflater = LayoutInflater.from(context);
//        this.mSettingItems.addAll(settingItemList);
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return mSettingItems.get(position).getType();
//    }
//
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        if (viewType == SettingItem.SETTING_NORMAL) {
//            return new NormalViewHolder(mLayoutInflater.inflate(R.layout.item_setting_normal, parent, false));
//        } else if (viewType == SettingItem.SETTING_CLOSE){
//            return new QuitViewHolder(mLayoutInflater.inflate(R.layout.item_setting_quit,parent, false));
//        } else {
//            return new BlankViewlHolder(mLayoutInflater.inflate(R.layout.item_setting_blank, parent, false));
//        }
//    }
//
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        SettingItem item = mSettingItems.get(position);
//        if (holder instanceof NormalViewHolder) {
//            ((NormalViewHolder) holder).mTitleTextView.setText(item.getTitle());
//            ((NormalViewHolder) holder).mIconImageView.setImageResource(item.getIconId());
//
//        } else if (holder instanceof QuitViewHolder) {
//            ((QuitViewHolder) holder).mTitleTextView.setText(item.getTitle());
//        }else if (holder instanceof BlankViewlHolder) {
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return mSettingItems.size();
//    }
//
//
//    public static class NormalViewHolder extends RecyclerView.ViewHolder {
//        TextView mTitleTextView;
//        ImageView mIconImageView;
//        NormalViewHolder(View view) {
//            super(view);
//            mTitleTextView = (TextView)view.findViewById(R.id.text_setting_title);
//            mIconImageView = (ImageView)view.findViewById(R.id.image_icon);
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    LogUtil.d("NormalViewHolder", "onClick--> position = " + getPosition());
//                }
//            });
//        }
//    }
//    public static class BlankViewlHolder extends RecyclerView.ViewHolder {
//        BlankViewlHolder(View view) {
//            super(view);
//        }
//    }
//    public static class QuitViewHolder extends RecyclerView.ViewHolder {
//        private TextView mTitleTextView;
//
//        QuitViewHolder(View view) {
//            super(view);
//            mTitleTextView = (TextView)view.findViewById(R.id.text_setting_title);
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    LogUtil.d("CloseViewHolder", "onClick--> position = " + getPosition());
//                }
//            });
//        }
//    }
//}
