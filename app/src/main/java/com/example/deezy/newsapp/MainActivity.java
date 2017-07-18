package com.example.deezy.newsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import static com.example.deezy.newsapp.R.string.ign;
import static com.example.deezy.newsapp.R.string.recode;
import static com.example.deezy.newsapp.R.string.techcrunch;
import static com.example.deezy.newsapp.R.string.techradar;

public class MainActivity extends AppCompatActivity {

    public String ARTICLE_REQUEST_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void searchFunc(View view) {
        EditText input = (EditText) findViewById(R.id.input);
        CharSequence userInput = input.getText();
        String userInputString = userInput.toString();
        userInputString = userInputString.replace(" ", "-");

        ARTICLE_REQUEST_URL = "https://content.guardianapis.com/search?q=" + userInputString + "&api-key=test";

        Log.i("URL", ARTICLE_REQUEST_URL);
        Intent NQIntent = new Intent(MainActivity.this, ResultsActivity.class);
        NQIntent.putExtra("AURL", ARTICLE_REQUEST_URL);
        startActivity(NQIntent);
    }
}
