package com.himeetu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.siyamed.shapeimageview.RoundedImageView;
import com.himeetu.R;
import com.himeetu.model.ActiveUsers;

import java.util.List;

/**
 * Created by zhangshuaiqi on 2015/12/20.
 */
public class DetailsRecyclerAdapter extends RecyclerView.Adapter<DetailsRecyclerAdapter.DetailsViewHolder> {
    private Context context;
    public List<ActiveUsers> mList;
    public DetailsRecyclerAdapter(Context context, List<ActiveUsers> mList){
        this.context = context;
        this.mList = mList;
    }
    @Override
    public DetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DetailsViewHolder detailsViewHolder = new DetailsViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_details_recyclerview,parent,false));
        return detailsViewHolder;
    }

    @Override
    public void onBindViewHolder(DetailsViewHolder holder, int position) {
        if(position != 0){
            holder.item_promoter.setVisibility(View.GONE);
        }else {
            holder.item_promoter.setVisibility(View.VISIBLE);
        }
//        holder.roundedImageView
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    //设置viewholder
    class DetailsViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView roundedImageView;
        TextView item_promoter;
        public DetailsViewHolder(View itemView) {
            super(itemView);
            roundedImageView = (RoundedImageView) itemView.findViewById(R.id.item_head_portrait);
            item_promoter = (TextView) itemView.findViewById(R.id.item_promoter);
        }
    }
}
