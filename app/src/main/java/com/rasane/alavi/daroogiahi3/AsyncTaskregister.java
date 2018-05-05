package com.rasane.alavi.daroogiahi3;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by alavi on 2/22/2018.
 */

class AsyncTaskregister extends AsyncTask {
    public String link="";
    public String mobile="";
    public String name="";
//    ProgressDialog PD = new ProgressDialog(new LoginActivity());
    public String command="";

    public AsyncTaskregister(String link,String command, String name,String mobile){

        this.link=link;
        this.mobile=mobile;
        this.name=name;
        this.command=command;




    }




    @Override
    protected Object doInBackground(Object[] params) {

        try{

            String data=URLEncoder.encode("command","UTF8")+"="+URLEncoder.encode((command),"UTF8");
            data+="&"+ URLEncoder.encode("name","UTF8")+"="+ URLEncoder.encode(name,"UTF8");
            data+="&"+URLEncoder.encode("mobile","UTF8")+"="+URLEncoder.encode((mobile),"UTF8");

            URL url=new URL(link);
            URLConnection connection=url.openConnection();

            connection.setDoOutput(true);
            OutputStreamWriter writer=new OutputStreamWriter(connection.getOutputStream());
            writer.write(data);
            writer.flush();
            BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder=new StringBuilder();

            String line=null;

            while((line=reader.readLine())!=null){
                builder.append(line);

            }


            LoginActivity.all=builder.toString();

        }catch (Exception e){

        }
        return "";
    }

    }


