package com.rasane.alavi.daroogiahi3;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
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

public class CodeActivity extends AppCompatActivity {

    SharedPreferences setting;

    EditText txt_code;
    AppCompatButton btn_submit;
    String code;
    String mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);

        setting = PreferenceManager.getDefaultSharedPreferences(this);

        txt_code=(EditText)findViewById(R.id.txt_code);
        btn_submit=(AppCompatButton)findViewById(R.id.btn_submit);

        mobile=getIntent().getStringExtra("mobile");

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                code=txt_code.getText().toString().trim();
                if(code.length()==4)
                {//code is valid
                    new verify_code().execute();
                }else
                {
                    Toast.makeText(getApplicationContext(), "کد وارد شده صحیح نمیباشد ! ... .", Toast.LENGTH_SHORT).show();
                }}
        });
    }
    public  class verify_code extends AsyncTask<Void,Void,String>
    {
        ProgressDialog pd=new ProgressDialog(CodeActivity.this);

        String url="http://192.168.1.102/daroogiahi-2/daroows/api.php";
        @Override
        protected String doInBackground(Void... voids) {
            ArrayList<NameValuePair> namevaluepairs=new ArrayList<NameValuePair>();
            namevaluepairs.add(new BasicNameValuePair("command","verify_code"));
            namevaluepairs.add(new BasicNameValuePair("code",code));
            namevaluepairs.add(new BasicNameValuePair("mobile",mobile));
            try {
                HttpClient httpclient=new DefaultHttpClient();
                HttpPost httppost=new HttpPost(url);
                httppost.setEntity(new UrlEncodedFormEntity(namevaluepairs, HTTP.UTF_8));
                HttpResponse httpresponse=httpclient.execute(httppost);
                final String response= EntityUtils.toString(httpresponse.getEntity());
                JSONObject jo=new JSONObject(response);
                if (!jo.getString("result").equals("error"))
                {//not error
                    SharedPreferences.Editor editor=setting.edit();
                    editor.putInt("user_id",jo.getInt("user_id"));
                    editor.commit();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            Toast.makeText(getApplicationContext(),"Login ...",Toast.LENGTH_SHORT).show();
                        }
                    });
                    CodeActivity.this.finish();
                }
                else {//show error
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),"خطا در تایید !...",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected  void onPostExecute(String result)
        {
            super.onPostExecute(result);
            pd.hide();
            pd.dismiss();
        }}
}
