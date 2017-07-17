package com.example.deezy.newsapp;

import android.content.AsyncTaskLoader;

        import android.content.AsyncTaskLoader;
        import android.content.Context;

        import com.example.deezy.newsapp.QueryUtils;

        import java.util.List;

/**
 * Loads a list of earthquakes by using an AsyncTask to perform the
 * network request to the given URL.
 */
public class NewsLoaderActivity extends AsyncTaskLoader<List<Article>> {

    /**
     * Tag for log messages
     */

    private static final String LOG_TAG = NewsLoaderActivity.class.getName();

    /**
     * Query URL
     */
    private String mUrl;

    /**
     * Constructs a new {@link NewsLoaderActivity}.
     *
     * @param context of the activity
     * @param url     to load data from
     */
    public NewsLoaderActivity(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<Article> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.
        List<Article> articles = QueryUtils.fetchArticleData(mUrl);
        return articles;
    }
}