package com.himeetu.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.himeetu.R;
import com.himeetu.model.Country;
import com.himeetu.util.LogUtil;

public class SortAdapter extends BaseAdapter implements SectionIndexer{
	private List<Country> list = null;
	private Context mContext;
	private Country lastSelectedCountry;

	public SortAdapter(Context mContext, List<Country> list) {
		this.mContext = mContext;
		this.list = list;
	}
	

	public void updateListView(List<Country> list){
		this.list = list;
		notifyDataSetChanged();
	}

	public int getCount() {
		return this.list.size();
	}

	public Object getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View view, ViewGroup arg2) {
		ViewHolder viewHolder = null;
		final Country mContent = list.get(position);
		if (view == null) {
			viewHolder = new ViewHolder();
			view = LayoutInflater.from(mContext).inflate(R.layout.item, null);
			viewHolder.tvTitle = (TextView) view.findViewById(R.id.title);
			viewHolder.tvLetter = (TextView) view.findViewById(R.id.catalog);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		
		int section = getSectionForPosition(position);
		
		if(position == getPositionForSection(section)){
			viewHolder.tvLetter.setVisibility(View.VISIBLE);
			viewHolder.tvLetter.setText(mContent.getSortLetters());
		}else{
			viewHolder.tvLetter.setVisibility(View.GONE);
		}
	
		viewHolder.tvTitle.setText(String.format("%s",this.list.get(position).getEnName()));

		viewHolder.tvTitle.setSelected(mContent.isSelected());

		if(mContent.isSelected()){
			lastSelectedCountry = mContent;
		}
		return view;

	}

	public void setSelected(Country country){
		if(lastSelectedCountry ==  country){
			return;
		}

		country.setSelected(true);

		notifyDataSetChanged();
	}

	@Override
	public void notifyDataSetChanged() {
		super.notifyDataSetChanged();
		if(lastSelectedCountry != null){
			lastSelectedCountry.setSelected(false);
		}
	}

	final static class ViewHolder {
		TextView tvLetter;
		TextView tvTitle;
	}


	public int getSectionForPosition(int position) {
		return list.get(position).getSortLetters().charAt(0);
	}


	public int getPositionForSection(int section) {
		for (int i = 0; i < getCount(); i++) {
			String sortStr = list.get(i).getSortLetters();
			char firstChar = sortStr.toUpperCase().charAt(0);
			if (firstChar == section) {
				LogUtil.d("getPositionForSection", String.format("firstChar=%s, section=%d", firstChar, section));

				return i;
			}
		}
		
		return -1;
	}
	

	private String getAlpha(String str) {
		String  sortStr = str.trim().substring(0, 1).toUpperCase();
		if (sortStr.matches("[A-Z]")) {
			return sortStr;
		} else {
			return "#";
		}
	}

	@Override
	public Object[] getSections() {
		return null;
	}
}