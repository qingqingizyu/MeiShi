package com.example.administrator.android_a1607_okhttp.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
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
 * Created by Administrator on 2016/9/21 0021.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder>implements View.OnClickListener{

//    定义数据源
    List<Bean> mList;
    private Context mContext;

    //构造函数传值
    public MyAdapter(List<Bean> list,Context context){
        this.mList=list;
        this.mContext=context;
    }
    //定义点击事件的接口
    public interface  OnRecyclerViewItemClickListener{
        void onItemClick(View view,int position);
    }

    private static OnRecyclerViewItemClickListener mListener;
    //设置条目点击事件
    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener listener){
        mListener=listener;
    }




    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
    //绑定数据
        Bean bean = mList.get(position);
        String recommend_cover_pic = bean.getRecommend_cover_pic(); //logo
        String likes_count = bean.getLikes_count(); //喜欢数量
        String recommend_caption = bean.getRecommend_caption();//标题
        holder.title.setText(recommend_caption);
        holder.likeCount.setText("喜欢:"+likes_count);

        //with:初始化；load:加载url对应的图片；into：展示图片
       Picasso.with(mContext).load(recommend_cover_pic).into(holder.logo);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onClick(View v) {

    }

    static class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView logo;
        TextView title,likeCount;

        public MyHolder(View itemView) {
            super(itemView);
            logo=(ImageView) itemView.findViewById(R.id.image_logo);
            title= (TextView) itemView.findViewById(R.id.tv_title);
            likeCount= (TextView) itemView.findViewById(R.id.tv_like);
            logo.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener!=null) {
                //获取子控件在布局中显示的位置
                int layoutPosition=getLayoutPosition();
                //获取在适配器中的位置
                int adapterPosition = getAdapterPosition();
                Log.i("TAG", "layoutPosition=" + layoutPosition + ",adapterPosition=" + adapterPosition);
                mListener.onItemClick(v,adapterPosition);
            }
        }
    }
}
