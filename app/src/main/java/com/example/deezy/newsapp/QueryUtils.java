package com.example.deezy.newsapp;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.R.string.no;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;



public final class QueryUtils {


    private QueryUtils() {
    }


    public static final String LOG_TAG = QueryUtils.class.getSimpleName();


    public static List<Article> fetchArticleData(String requestUrl) {

        URL url = createUrl(requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }
        List<Article> articles = extractArticle(jsonResponse);

        return articles;
    }


    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }


    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();


            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the article JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }


    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    public static List<Article> extractArticle(String articleJSON) {
        if (TextUtils.isEmpty(articleJSON)) {
            return null;
        }

        List<Article> articles = new ArrayList<Article>();

        JSONArray items;

        try {
            JSONObject root = new JSONObject(articleJSON);
            try {
                items = root.getJSONArray("articles");
            } catch (JSONException e) {
                Log.e(LOG_TAG, "No articles available", e);
                items = null;
            }

            for (int i = 0; i < items.length(); i++) {
                String Url;
                String ImageUrl;
                String Author;
                String Title;
                String Description;

                JSONObject it = items.getJSONObject(i);
                try {
                    Url = it.getString("url");
                } catch (JSONException e) {
                    Log.e(LOG_TAG, "no url available", e);
                    Url = "No url available";
                }
                try {
                    ImageUrl = it.getString("urlToImage");
                } catch (JSONException e) {
                    Log.e(LOG_TAG, "No image url available", e);
                    ImageUrl = "No image url available";
                }
                try {
                    Author = it.getString("author");
                } catch (JSONException e) {
                    Log.e(LOG_TAG, "No author available", e);
                    Author = "No Author available";
                }


                try {
                    Title = it.getString("title");
                } catch (JSONException e) {
                    Log.e(LOG_TAG, "No title available", e);
                    Title = "No title available";
                }

                try {
                    Description = it.getString("description");
                } catch (JSONException e) {
                    Log.e(LOG_TAG, "no description available");
                    Description = "No description available";
                }


                URL url = null;
                try {
                    url = new URL("http://www.theshanngroup.com/wp-content/uploads/2017/05/no-image-available.jpg");
                } catch (MalformedURLException e){
                    Log.e(LOG_TAG,"malformed url");
                }
                try {
                     url = new URL(ImageUrl);
                }catch (MalformedURLException e){
                    Log.e(LOG_TAG,"malformed URL");
                }
                Bitmap bmp;
                try {
                     bmp = BitmapFactory.decodeStream(
                        url.openConnection().getInputStream());

                }catch (IOException e){
                    Log.e(LOG_TAG,"url connection failed");
                    bmp = null;
                }
                articles.add(new Article(Url,bmp,Author,Title,Description));

            }
        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the book JSON results", e);
        }
        return articles;
    }
}