package com.rasane.alavi.daroogiahi3;

/**
 * Created by Amin on 27/02/2018.
 */


import android.os.AsyncTask;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

    /**
     * Created by General on 12/4/2016.
     */
    public class AsyncTaskReadBasket extends AsyncTask {
        public String link="";
        public String itemid="";

        public AsyncTaskReadBasket(String link,String itemid){

            this.link=link;
            this.itemid=itemid;
        }
        @Override
        protected Object doInBackground(Object[] params) {

            try{
                String data= URLEncoder.encode("itemid","UTF8")+"="+URLEncoder.encode(itemid,"UTF8");

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
                ActivityShow.info=builder.toString();

            }catch (Exception e){

            }
            return "";
        }
    }



