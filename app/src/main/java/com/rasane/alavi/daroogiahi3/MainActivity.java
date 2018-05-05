package com.rasane.alavi.daroogiahi3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

//import co.ronash.pushe.Pushe;

public class MainActivity extends ActivityEnhanced {
    private boolean exit = false;
    OnlineCheck sharedPref;
    String PERMISSION = android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
//        Pushe.initialize(this,true);

        sharedPref=new OnlineCheck(this) ;
        if (sharedPref.isFirstLaunch()) {
//            startActivity(new Intent(this, SplashActivity.class));
                Toast.makeText(G.context,"برنامه های جدید در ورژن های بعدی برناهه افزوده خواهند شد!", Toast.LENGTH_LONG).show();

            sharedPref.setFirstLaunch(false);
        }

             findViewById(R.id.txtfehrest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   G.startActivity(Activity_category.class,false);
                }
                    });
//        findViewById(R.id.txtexit).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressed();
//            }
//        });
//        findViewById(R.id.txtshop).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(G.context,"فروشگاه در تاریخ 29-2-97 راه اندازی خواهد شد!", Toast.LENGTH_LONG).show();
//            }
//        });
//        findViewById(R.id.txtOtherPro).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(G.context,"برنامه های جدید در ورژن های بعدی برناهه افزوده خواهند شد!", Toast.LENGTH_LONG).show();
//            }
//        });
// findViewById(R.id.basket).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                G.startActivity(ActivityBasket.class,false);
//
//            }
//        });
    }
        @Override
        public void onBackPressed() {
            if (exit) {
                System.exit(0);
            } else {
                exit = true;
                Toast.makeText(G.context, R.string.confirm_exit, Toast.LENGTH_SHORT).show();
                G.hanler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        exit = false;
                    }
                }, 2000);
            }
        }
}
