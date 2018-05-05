package com.rasane.alavi.daroogiahi3;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class LoginActivity extends ActivityEnhanced {
    public static String all="";
    SharedPreferences setting;

    AppCompatButton btn_submit;
    EditText txt_mobile;
    EditText txt_name;
    String mobile="";
    String name="";
    String command = "register_user";

    ProgressDialog dialog;
    public LoginActivity() throws JSONException {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);
        setting = PreferenceManager.getDefaultSharedPreferences(this);
        dialog = new ProgressDialog(LoginActivity.this);

        txt_mobile=(EditText)findViewById(R.id.txt_mobile);
        txt_name=(EditText)findViewById(R.id.txt_name);
        btn_submit=(AppCompatButton)findViewById(R.id.btn_submit) ;


        btn_submit.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                dialog.setMessage("Its loading....");
                dialog.setTitle("ProgressDialog bar example");
                dialog.show();
                mobile=txt_mobile.getText().toString().trim();

                name=txt_name.getText().toString().trim();

                if (name.length()!=0) {
                    if (mobile.length() == 11) {
                        if (mobile.startsWith("09")) {//mobile number and name is true
                            new AsyncTaskregister("http://192.168.1.102/daroogiahi-2/daroows/api.php",command,name,mobile).execute();

                            final Timer timer=new Timer();
                            timer.scheduleAtFixedRate(new TimerTask() {
                                @Override
                                public void run() {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (!LoginActivity.all.equals("")) {
                                                try {
                                                JSONObject jo=new JSONObject(all);
                                                if (jo.getString("result").equals("ok"))
                                                {
                                                    //activation key sent
                                                Intent i=new Intent(LoginActivity.this,CodeActivity.class);
                                                    i.putExtra("mobile",mobile);
                                                    startActivity(i);
                                                    LoginActivity.this.finish();
                                                    Toast.makeText(G.context,"کد تایید برای شما ارسال گردید !",Toast.LENGTH_SHORT).show();
                                                    timer.cancel();
                                                    dialog.hide();
                                                    dialog.dismiss();
                                                }
                                                } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            }
                                        }
                    });
                }
            },1,1000);

                        } else {
                            Toast.makeText(getApplicationContext(), "Mobile number is wrong .", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Mobile number length is wrong .", Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Name dont entered ... .", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}
