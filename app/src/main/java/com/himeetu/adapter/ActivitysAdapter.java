package com.himeetu.adapter;

import android.content.ComponentName;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.himeetu.R;
import com.himeetu.model.ListItem;
import com.himeetu.ui.my.ActivitysFragment;
import com.himeetu.util.DateUtils;
import com.himeetu.util.LogUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ActivitysAdapter extends RecyclerView.Adapter<ActivitysAdapter.ViewHolder> {
    private Context mContext;
    private final List<ListItem> mValues;
    private final ActivitysFragment.OnListFragmentInteractionListener mListener;

    public ActivitysAdapter(Context context, List<ListItem> items, ActivitysFragment.OnListFragmentInteractionListener listener) {
        mContext = context;
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fragment_activitys, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        if(TextUtils.isEmpty(holder.mItem.getTime()) || holder.mItem == null ){
            holder.mIdView.setText("00/00");
        }else {
            holder.mIdView.setText(DateUtils.format(DateUtils.parse(holder.mItem.getTime()), "dd/MM"));
        }

        LogUtil.d("ActivitysAdapter", holder.mItem.getImgPath());
        Picasso.with(mContext).load(holder.mItem.getImgPath()).placeholder(R.drawable.img_default)
                .error(R.drawable.img_default).into( holder.mImageView);
//        holder.mIdView.setText(mValues.get(position).id);
//        holder.mImageView.setText(mValues.get(position).content);

//        holder.mView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (null != mListener) {
//                    // Notify the active callbacks interface (the activity, if the
//                    // fragment is attached to one) that an item has been selected.
//                    mListener.onListFragmentInteraction(holder.mItem);
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final ImageView mImageView;
        public ListItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.tv_date);
            mImageView = (ImageView) view.findViewById(R.id.iv_img);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mIdView.getText() + "'";
        }
    }
}
