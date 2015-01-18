package com.chehanr.dolanpls;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


public class AboutActivity extends Activity {
    TextView about;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_about);

        TextView github = (TextView) findViewById(R.id.github);
        github.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/chehanr"));
                startActivity(browserIntent);
            }
        });
        TextView tumblr = (TextView) findViewById(R.id.tumblr);
        tumblr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://chehanr.tumblr.com/"));
                startActivity(browserIntent);
            }
        });
        TextView twitter = (TextView) findViewById(R.id.twitter);
        twitter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.twitter.com/chehanr"));
                startActivity(browserIntent);
            }
        });
    }
}