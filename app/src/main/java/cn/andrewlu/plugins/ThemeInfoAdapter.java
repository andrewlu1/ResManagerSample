package cn.andrewlu.plugins;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import cn.andrewlu.resmanager.dao.ThemeInfo;

/**
 * Created by andrewlu on 2018/1/22.
 */

public class ThemeInfoAdapter extends BaseAdapter {
    private Context mContext;
    private List<ThemeInfo> mInfoList;
    private LayoutInflater mLayoutInflater;

    public ThemeInfoAdapter(Context context, List<ThemeInfo> infoList) {
        this.mContext = context;
        this.mInfoList = infoList;
        mLayoutInflater = LayoutInflater.from(context);
        if (mInfoList == null) mInfoList = Collections.emptyList();
    }

    @Override
    public int getCount() {
        return mInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return mInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_theme_list, parent, false);
        }
        TextView name = convertView.findViewById(R.id.themName);
        TextView desc = convertView.findViewById(R.id.descript);
        TextView ver = convertView.findViewById(R.id.version);
        TextView author = convertView.findViewById(R.id.author);
        CheckBox checkBox = convertView.findViewById(R.id.selected);

        ThemeInfo info = (ThemeInfo) getItem(position);
        name.setText(info.name);
        desc.setText(info.description);
        ver.setText(info.verName);
        author.setText(info.author);
        checkBox.setChecked(info.selected);

        return convertView;
    }
}
