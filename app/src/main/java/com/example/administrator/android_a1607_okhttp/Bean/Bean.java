package com.example.administrator.android_a1607_okhttp.Bean;

/**
 * Created by Administrator on 2016/9/21 0021.
 */

public class Bean {
    private String recommend_caption; //标题
    private String recommend_cover_pic; //logo
    private String caption; //小标题
    private String video; //视频
    private String likes_count;//喜欢人数
    private String type;

    public Bean() {
    }

    public Bean(String recommend_caption, String recommend_cover_pic, String caption, String video, String likes_count, String type) {
        this.recommend_caption = recommend_caption;
        this.recommend_cover_pic = recommend_cover_pic;
        this.caption = caption;
        this.video = video;
        this.likes_count = likes_count;
        this.type = type;
    }

    public String getRecommend_caption() {
        return recommend_caption;
    }

    public void setRecommend_caption(String recommend_caption) {
        this.recommend_caption = recommend_caption;
    }

    public String getRecommend_cover_pic() {
        return recommend_cover_pic;
    }

    public void setRecommend_cover_pic(String recommend_cover_pic) {
        this.recommend_cover_pic = recommend_cover_pic;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getLikes_count() {
        return likes_count;
    }

    public void setLikes_count(String likes_count) {
        this.likes_count = likes_count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
