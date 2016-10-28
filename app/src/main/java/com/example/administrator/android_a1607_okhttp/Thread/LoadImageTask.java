package com.example.administrator.android_a1607_okhttp.Thread;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;


import com.example.administrator.android_a1607_okhttp.Url.HttpUtils;

import java.io.IOException;

/**
 * Created by Administrator on 2016/8/27.
 */
public class LoadImageTask extends AsyncTask<String, Void, Bitmap> {

    public interface OnGetImageResultListener {
        void onGetImageResult(Bitmap result);
    }

    private OnGetImageResultListener mListener;

    public LoadImageTask(OnGetImageResultListener listener) {
        this.mListener = listener;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        try {
            byte[] data = HttpUtils.loadData(params[0]);
            return BitmapFactory.decodeByteArray(data, 0, data.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);
        if (result != null && mListener != null) {
            mListener.onGetImageResult(result);
        }
    }


}
