package com.example.administrator.android_a1607_okhttp.activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.administrator.android_a1607_okhttp.Bean.Bean;
import com.example.administrator.android_a1607_okhttp.DataBase.NewsDao;
import com.example.administrator.android_a1607_okhttp.R;

public class See_Collect_Activity extends AppCompatActivity {

    private ListView collection;
    private String sql;
    private NewsDao dao;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_see__collect_);
        initView();//初始化
        initSelectDataBase(); //查询数据库
    }


    //初始化
    private void initView() {
        collection = (ListView) findViewById(R.id.lv_collection);
    }
    //查询数据库
    private void initSelectDataBase() {
        dao = new NewsDao(this);
        sql = "select * from collect";
        Cursor cursor = dao.select(sql, null);
        adapter = new SimpleCursorAdapter(this, R.layout.list_collection_item,cursor,new String[]{"content"},new int[]{R.id.tv_collecct_item}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        collection.setAdapter(adapter);
        initRemoveCollection();//移除收藏的功能
    }
    //长按移除方法
    private void initRemoveCollection() {
        collection.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor= (Cursor) adapter.getItem(position);
                int delete = dao.delete("collect", "news_id=?",
                        new String[]{cursor.getString(cursor
                                .getColumnIndex("news_id"))});
                cursor.close();
                if (delete > 0) {
                    // 重新查询数据库.刷新界面.
                    Cursor newCursor = dao.select(sql, null);
                    //交换Cursor结果的方法.
                    adapter.swapCursor(newCursor);
                    Toast.makeText(See_Collect_Activity.this, "删除成功!", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
        //点击收藏条目查看视频
        collection.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) adapter.getItem(position);
                String video = cursor.getString(cursor.getColumnIndex("news_id"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                Intent intent = new Intent(See_Collect_Activity.this, SeeMovieActivity.class);
                    intent.putExtra("video", video);
                    intent.putExtra("content", content);
                    startActivity(intent);
            }
        });
    }

}
