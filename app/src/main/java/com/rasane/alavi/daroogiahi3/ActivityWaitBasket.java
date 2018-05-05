package com.rasane.alavi.daroogiahi3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class ActivityWaitBasket extends ActivityEnhanced {
    public static SharedPreferences setting;
    public static String data="";
//    public static String data="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_basket);

        setting= PreferenceManager.getDefaultSharedPreferences(G.context);
        int userid= setting.getInt("user_id",0);
        if (setting.getInt("user_id", 0) == 0) {//not login
            Toast.makeText(G.context,"سبد خرید شما خالی میباشد!",Toast.LENGTH_LONG).show();
            ActivityWaitBasket.this.finish();
        } else {

            new AsyncTaskShowBasket("http://192.168.1.102/daroogiahi-2/daroows/showbasket.php",userid).execute();

            final Timer timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if(!ActivityFinalBasket.data.equals("") )  {

                            Intent intent=new Intent(G.context,ActivityFinalBasket.class);
//                            intent.putExtra("data",data);
                            startActivity(intent);

//                            Toast.makeText(G.context,data,Toast.LENGTH_SHORT).show();
                            timer.cancel();
                            finish();
                        }
                    }
                });
            }
        },1,1000);





    }}
}
