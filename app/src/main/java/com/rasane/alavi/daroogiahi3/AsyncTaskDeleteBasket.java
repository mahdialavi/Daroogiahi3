package com.rasane.alavi.daroogiahi3;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Amin on 04/03/2018.
 */

public class AsyncTaskDeleteBasket extends AsyncTask {

    public String link="";
    public String idproduct;
    public int userid;


    public AsyncTaskDeleteBasket(String link,String idproduct,int userid){

        this.link=link;
        this.idproduct=idproduct;
        this.userid=userid;
    }
    @Override
    protected Object doInBackground(Object[] params) {

        try{
            String data= URLEncoder.encode("idproduct","UTF8")+"="+URLEncoder.encode(idproduct,"UTF8");
            data+="&"+URLEncoder.encode("userid","UTF8")+"="+URLEncoder.encode(String.valueOf(userid),"UTF8");

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

            ActivityFinalBasket.data=builder.toString();

        }catch (Exception e){

        }
        return "";
    }

}