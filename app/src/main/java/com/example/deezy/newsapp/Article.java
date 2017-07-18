package com.example.deezy.newsapp;

import android.graphics.Bitmap;
import android.media.Image;

/**
 * Created by DEEZY on 17/07/2017.
 */

public class Article {
    private String mUrl;
    private String mSection;
    private String mTitle;

    public Article(String Url, String Title, String Section){
        mUrl = Url;
        mSection = Section;
        mTitle = Title;
    }

    public String getmUrl() {
        return mUrl;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmSection() {
        return mSection;
    }
}
