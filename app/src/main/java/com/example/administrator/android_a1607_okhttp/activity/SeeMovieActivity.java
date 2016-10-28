package com.example.administrator.android_a1607_okhttp.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.administrator.android_a1607_okhttp.DataBase.NewsDao;
import com.example.administrator.android_a1607_okhttp.R;
import com.squareup.picasso.Picasso;

public class SeeMovieActivity extends AppCompatActivity {


    private VideoView video_view;
    private TextView content_view;
    private RadioButton button;
    private String video;
    private String content;
    private CollapsingToolbarLayout mCollapselayout;
    private ImageView ivDetail;
    private Toolbar mTool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_see_movie);
        Intent intent = getIntent();
        video = intent.getStringExtra("video");
        content = intent.getStringExtra("content");
        String logo = intent.getStringExtra("logo");
        String title = intent.getStringExtra("title");
        intitView();//初始化

        //视频
        video_view.setVideoURI(Uri.parse(video));
        video_view.start();
        //内容
        content_view.setText(content);
        mTool.inflateMenu(R.menu.over_flow_menu);
        mTool.setOverflowIcon(getResources().getDrawable(R.mipmap.ic_menu_more_overflow));
        mTool.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //设置图片
        Picasso.with(this).load(logo).into(ivDetail);

        //设置标题
        mCollapselayout.setTitle(title);
        //设置折叠时文字的颜色
        mCollapselayout.setCollapsedTitleTextColor(Color.WHITE);
        //设置展开时文字的颜色
        mCollapselayout.setExpandedTitleColor(Color.parseColor("#888888"));


        initCollectView(); //收藏实现

    }

    //初始化
    private void intitView() {
        video_view = (VideoView) findViewById(R.id.video_view);
        content_view = (TextView) findViewById(R.id.tv_content);
        button = (RadioButton) findViewById(R.id.btn_collect);
        mCollapselayout = (CollapsingToolbarLayout) findViewById(R.id.collapse_layout);
        ivDetail = (ImageView) findViewById(R.id.iv_detail);
        mTool = (Toolbar) findViewById(R.id.tool_detail);
    }

    //收藏实现
    private void initCollectView() {
        final NewsDao dao=new NewsDao(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues values=new ContentValues();
                values.put("news_id",video);
                values.put("content",content);
                long insert = dao.insert("collect", values);
                if (insert > 0) {
                    Toast.makeText(SeeMovieActivity.this, "收藏成功!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    public void onBackPressed() { //按返回键退出
        super.onBackPressed();
        finish();
    }
}
