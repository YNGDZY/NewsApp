package com.example.deezy.newsapp;

import android.graphics.Bitmap;
import android.media.Image;

/**
 * Created by DEEZY on 17/07/2017.
 */

public class Article {
    private String mUrl;
    private Bitmap mImage;
    private String mAuthor;
    private String mTitle;
    private String mDescription;

    public Article(String Url,Bitmap Image, String Author, String Title, String Description){
        mUrl = Url;
        mImage = Image;
        mAuthor = Author;
        mTitle = Title;
        mDescription = Description;
    }

    public String getmUrl() {
        return mUrl;
    }

    public Bitmap getmImage() {
        return mImage;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }
}
