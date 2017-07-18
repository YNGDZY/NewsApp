package com.example.deezy.newsapp;

import android.content.AsyncTaskLoader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.example.deezy.newsapp.QueryUtils;

import java.util.List;

public class NewsLoaderActivity extends AsyncTaskLoader<List<Article>> {

    private static final String LOG_TAG = NewsLoaderActivity.class.getName();
    
    private String mUrl;

    public NewsLoaderActivity(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }


    @Override
    public List<Article> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        List<Article> articles = QueryUtils.fetchArticleData(mUrl);
        return articles;
    }
}