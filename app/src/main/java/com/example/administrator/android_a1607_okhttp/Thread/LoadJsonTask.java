package com.example.administrator.android_a1607_okhttp.Thread;

import android.os.AsyncTask;

import com.example.administrator.android_a1607_okhttp.Bean.Bean;
import com.example.administrator.android_a1607_okhttp.Url.HttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/8/27.
 */
public class LoadJsonTask extends AsyncTask<String, Void, List<Bean>> {




    public interface OnGetJsonResultListener {
        void onGetJsonResult(List<Bean> result);
    }

    private OnGetJsonResultListener mListener;

    public LoadJsonTask(OnGetJsonResultListener listener){
        mListener=listener;
    }

    protected List<Bean> doInBackground(String... params) {
        try {
            byte[] data = HttpUtils.loadData(params[0]);

            ArrayList<Bean> list = new ArrayList<>();
            String json = new String(data);
            JSONArray jsonArray=new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                Bean bean = new Bean();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String recommend_caption = jsonObject.getString("recommend_caption");
                String recommend_cover_pic = jsonObject.getString("recommend_cover_pic");
                String type = jsonObject.getString("type");
                if (!jsonObject.getString("type").equals("media")) {

                }else {
                    JSONObject obj=jsonObject.getJSONObject("media");
                    String caption = obj.getString("caption");
                    String video = obj.getString("video");
                    String likes_count = obj.getString("likes_count");
                    bean.setCaption(caption);
                    bean.setVideo(video);
                    bean.setLikes_count(likes_count);
                }
                bean.setRecommend_caption(recommend_caption);
                bean.setRecommend_cover_pic(recommend_cover_pic);
                bean.setType(type);
                list.add(bean);
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Bean> result) {
        super.onPostExecute(result);
        if (mListener != null && result != null) {
            mListener.onGetJsonResult(result);
        }
    }

}
