package com.certusnet.common.adapter;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 公有adapter处理类
 * 
 * @author lig
 * 
 */
public abstract class CommonAdapter extends BaseAdapter implements
		AdapterView.OnItemClickListener {
	protected List<Object> data = new ArrayList<Object>();

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int location) {
		return data.get(location);
	}

	@Override
	public long getItemId(int id) {
		return id;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return null;
	}

	/**
	 * 请求加载数据
	 */
	public abstract void request();

	public class ViewHolder {
		public TextView name;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

	}
}
