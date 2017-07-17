package com.example.deezy.newsapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.widget.TextView;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;



import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;
import static android.provider.ContactsContract.CommonDataKinds.Website.URL;


public class ResultsActivity extends AppCompatActivity implements LoaderCallbacks<List<Article>> {
    public String ARTICLE_REQUEST_URL;
    private ArticleAdapter mAdapter;
    public static final String LOG_TAG = ResultsActivity.class.getName();
    private static final int ARTICLE_LOADER_ID = 1;

    @Override
    public Loader<List<Article>> onCreateLoader(int i, Bundle bundle) {
        return new NewsLoaderActivity(this, ARTICLE_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Article>> loader, List<Article> books) {
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);
        mEmptyStateTextView.setText(R.string.no_articles);

        mAdapter.clear();
        if (books != null && !books.isEmpty()) {
            mAdapter.addAll(books);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Article>> loader) {
        mAdapter.clear();
    }

    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ARTICLE_REQUEST_URL = getIntent().getStringExtra("AURL");

        ListView articleListView = (ListView) findViewById(R.id.list);

        mAdapter = new ArticleAdapter(this, new ArrayList<Article>());

        articleListView.setAdapter(mAdapter);
        articleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                Article currentArticle = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri articleUri = Uri.parse(currentArticle.getmUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, articleUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        articleListView.setEmptyView(mEmptyStateTextView);

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(ARTICLE_LOADER_ID, null, this);
        } else {

            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }
    }
}