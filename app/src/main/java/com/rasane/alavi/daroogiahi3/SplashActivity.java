package com.rasane.alavi.daroogiahi3;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SplashActivity extends ActivityEnhanced {
    Button button;
    ProgressDialog progressDoalog;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash);


                                new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    G.startActivity(MainActivity.class,true);
                                    }
            },1000);

    }
            @Override
            public void onBackPressed () {
                Toast.makeText(G.context, "لطفا صبر کنید!", Toast.LENGTH_LONG).show();
            }


        }

