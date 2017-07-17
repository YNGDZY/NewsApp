package com.example.deezy.newsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    public String ARTICLE_REQUEST_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
    public void searchFunc(View view){
        RadioButton RB1 = (RadioButton) findViewById(R.id.RB1);
        Boolean techradar = RB1.isChecked();
        RadioButton RB2 = (RadioButton) findViewById(R.id.RB2);
        Boolean techcrunch = RB2.isChecked();
        RadioButton RB3 = (RadioButton) findViewById(R.id.RB3);
        Boolean ign = RB3.isChecked();
        RadioButton RB4 = (RadioButton) findViewById(R.id.RB4);
        Boolean recode = RB4.isChecked();
        RadioButton RB5 = (RadioButton) findViewById(R.id.RB5);
        Boolean hacker = RB5.isChecked();

        if(techradar){
            ARTICLE_REQUEST_URL =  "https://newsapi.org/v1/articles?source=" + getString(R.string.techradar) + "&sortBy=latest&apiKey=088b00fc0dae408fa4bb2450b7688fe0";
        }else if(techcrunch){
            ARTICLE_REQUEST_URL =  "https://newsapi.org/v1/articles?source=" + getString(R.string.techcrunch) + "&sortBy=latest&apiKey=088b00fc0dae408fa4bb2450b7688fe0";
        }else if(ign){
            ARTICLE_REQUEST_URL =  "https://newsapi.org/v1/articles?source=" + getString(R.string.ign) + "&sortBy=latest&apiKey=088b00fc0dae408fa4bb2450b7688fe0";
        }else if(recode){
            ARTICLE_REQUEST_URL =  "https://newsapi.org/v1/articles?source=" + getString(R.string.recode) + "&sortBy=latest&apiKey=088b00fc0dae408fa4bb2450b7688fe0";
        }else{
            ARTICLE_REQUEST_URL =  "https://newsapi.org/v1/articles?source=" + getString(R.string.hackernews) + "&sortBy=latest&apiKey=088b00fc0dae408fa4bb2450b7688fe0";
        }
        Log.i("URL", ARTICLE_REQUEST_URL);
        Intent NQIntent = new Intent(MainActivity.this, ResultsActivity.class);
        NQIntent.putExtra("AURL", ARTICLE_REQUEST_URL);
        startActivity(NQIntent);
    }
}
