package com.example.administrator.android_a1607_okhttp.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.administrator.android_a1607_okhttp.Adapter.MyGridAdapter;
import com.example.administrator.android_a1607_okhttp.Bean.Bean;
import com.example.administrator.android_a1607_okhttp.R;
import com.example.administrator.android_a1607_okhttp.activity.SeeMovieActivity;
import com.example.administrator.android_a1607_okhttp.Thread.LoadJsonTask;
import com.example.administrator.android_a1607_okhttp.Url.HttpUrl;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class meizhangFragment extends Fragment implements LoadJsonTask.OnGetJsonResultListener,AdapterView.OnItemClickListener {
    private List<Bean> data;
    private int nextpager=12;
    private int PagerNum = 1;
    private PullToRefreshGridView gridView;
    private MyGridAdapter gridAdapter;

    public meizhangFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meizhang, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView = (PullToRefreshGridView) view.findViewById(R.id.pull_grid2);
        initIndicator(); //设置上拉下拉刷新样式
        gridView.setMode(PullToRefreshBase.Mode.BOTH);
        loadNewData(); //开启一步任务
        gridView.setOnItemClickListener(this);
        gridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            //下拉
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> pullToRefreshBase) {
                String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME
                        | DateUtils.FORMAT_SHOW_DATE
                        | DateUtils.FORMAT_ABBREV_ALL);

                // Update the LastUpdatedLabel
                gridView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                PagerNum++;
                loadNewData();
            }
            //上拉
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> pullToRefreshBase) {

                nextpager+=6;
                loadNewData();
            }
        });

    }
    /**
     * 初始化数据
     */
    private void loadNewData() {
        String url = String.format(HttpUrl.JSON_MEIZHUANG, PagerNum);
        String path=url+"&&count="+nextpager;
        new LoadJsonTask(this).execute(path);
        gridView.onRefreshComplete();
    }




    @Override //回调接口 ,数据源
    public void onGetJsonResult(List<Bean> result) {
        data=result;
        gridAdapter = new MyGridAdapter(getActivity(),result);
        gridView.setAdapter(gridAdapter);

        gridView.onRefreshComplete();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), SeeMovieActivity.class);
        Bean bean = data.get(position);
        String video = bean.getVideo();//跳转电影地址
        String caption = bean.getCaption(); //跳转内容
        String type = bean.getType();//判断是否是视频
        String recommend_caption = bean.getRecommend_caption();
        String recommend_cover_pic = bean.getRecommend_cover_pic();
        if (type.equals("scheme")) {
            Toast.makeText(getActivity(), "不是视频资源！！！！！", Toast.LENGTH_LONG).show();
        } else {
            intent.putExtra("video", video);
            intent.putExtra("content", caption);
            intent.putExtra("logo",recommend_cover_pic);
            intent.putExtra("title",recommend_caption);
            startActivity(intent);
        }

    }
    private void initIndicator() {
        //设置上部
        ILoadingLayout startLabels = gridView.getLoadingLayoutProxy(true, false);
        startLabels.setPullLabel("下拉刷新...");// 刚下拉时，显示的提示
        startLabels.setRefreshingLabel("正在刷新...");// 刷新时
        startLabels.setReleaseLabel("松开刷新...");// 下来达到一定距离时，显示的提示
        //设置下部
        ILoadingLayout endLabels = gridView.getLoadingLayoutProxy(false, true);
        endLabels.setPullLabel("上拉刷新...");// 刚下拉时，显示的提示
        endLabels.setRefreshingLabel("正在刷新...");// 刷新时
        endLabels.setReleaseLabel("松开刷新...");// 下来达到一定距离时，显示的提示
    }
}
