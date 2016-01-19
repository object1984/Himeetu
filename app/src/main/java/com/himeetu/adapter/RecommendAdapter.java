package com.himeetu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.himeetu.R;
import com.himeetu.app.Constants;
import com.himeetu.model.Recommend;
import com.himeetu.util.LogUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by object1984 on 15/11/28.
 */
public class RecommendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private List<Recommend> mRecommendItems = new ArrayList<Recommend>();


    public RecommendAdapter(Context context, List<Recommend> recommendList) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mRecommendItems.addAll(recommendList);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new RecommendViewHolder(mLayoutInflater.inflate(R.layout.item_recommend, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Recommend item = mRecommendItems.get(position);
            ((RecommendViewHolder) holder).mTitleTextView.setText(item.getTitle());

        Picasso.with(mContext).load(item.getImgPath()).placeholder(R.drawable.img_default)
                .error(R.drawable.img_default).into(((RecommendViewHolder) holder).mIconImageView);
    }

    @Override
    public int getItemCount() {
        return mRecommendItems.size();
    }


    public static class RecommendViewHolder extends RecyclerView.ViewHolder {
        TextView mTitleTextView;
        ImageView mIconImageView;
        RecommendViewHolder(View view) {
            super(view);
            mTitleTextView = (TextView)view.findViewById(R.id.text_recommend);
            mIconImageView = (ImageView)view.findViewById(R.id.img_recommend);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtil.d("NormalViewHolder", "onClick--> position = " + getPosition());
                }
            });
        }
    }

    public void addAll(List<Recommend> recommends){
        this.mRecommendItems.addAll(recommends);
        this.notifyDataSetChanged();
    }

}
