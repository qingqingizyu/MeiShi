package com.example.administrator.android_a1607_okhttp.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.android_a1607_okhttp.Bean.Bean;
import com.example.administrator.android_a1607_okhttp.R;
import com.example.administrator.android_a1607_okhttp.Thread.LoadImageTask;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016/9/22 0022.
 */

public class MyGridAdapter extends BaseAdapter {
    private List<Bean> mData;
    private Context mContext;

    //利用构造方法传递参数
    public MyGridAdapter(Context context,List<Bean> data) {
        super();
        this.mContext = context;
        this.mData = data;
        notifyDataSetChanged();
    }

    //创建方法进行传递更新adapter所需要的数据源
    public void setData(List<Bean> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        //进行判断
        return mData != null ? mData.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        //进行判断
        return mData != null ? mData.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        //布局填充
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewHolder holder;
        //复用convertview
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.recycler, parent, false);
            holder = new ViewHolder();
            holder.tvLikeCount = (TextView) convertView.findViewById(R.id.tv_like);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            holder.ivShow = (ImageView) convertView.findViewById(R.id.image_logo);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Bean Bean = mData.get(position);
        String recommend_cover_pic = Bean.getRecommend_cover_pic();
        holder.tvTitle.setText(Bean.getRecommend_caption());
        holder.tvLikeCount.setText("喜欢:"+Bean.getLikes_count());
        Picasso.with(mContext).load(recommend_cover_pic).into(holder.ivShow);
        return convertView;
    }

    //设置静态类 ，防止内存泄漏
    static class ViewHolder {
        ImageView ivShow;
        TextView tvTitle, tvLikeCount;
    }
}
