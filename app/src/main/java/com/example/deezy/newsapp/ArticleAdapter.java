package com.example.deezy.newsapp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by DEEZY on 11/07/2017.
 */

public class ArticleAdapter extends ArrayAdapter<Article> {
    public ArticleAdapter(Activity context, ArrayList<Article> articleList) {

        super(context, 0, articleList);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        Article currentArticle = getItem(position);

        TextView titleText = (TextView) listItemView.findViewById(R.id.titleTextView);
        String title = currentArticle.getmTitle();
        titleText.setText(title);


        TextView authorText = (TextView) listItemView.findViewById(R.id.authorTextView);
        String authors = currentArticle.getmAuthor();
        authorText.setText(authors);

        TextView descriptionText = (TextView) listItemView.findViewById(R.id.descriptionTextView);
        String description = currentArticle.getmDescription();
        descriptionText.setText(description);

        ImageView image = (ImageView) listItemView.findViewById(R.id.newsImageView);
        Bitmap bmp = currentArticle.getmImage();
        if(bmp != null) {
            image.setImageBitmap(bmp);
        }else{
            image.setImageResource(R.drawable.noimage);
        }



        return listItemView;
    }
}
