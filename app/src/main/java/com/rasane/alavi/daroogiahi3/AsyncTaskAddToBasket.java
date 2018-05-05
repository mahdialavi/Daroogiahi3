package com.rasane.alavi.daroogiahi3;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by alavi on 2/22/2018.
 */

class AsyncTaskAddToBasket extends AsyncTask {
    public String link="";
    public int userid;
    public String itemid;

    public AsyncTaskAddToBasket(String link,int userid,String itemid){

        this.link=link;

        this.itemid=itemid;
        this.userid=userid;


    }




    @Override
    protected Object doInBackground(Object[] params) {

        try{

//            String data= URLEncoder.encode("type","UTF8")+"="+ URLEncoder.encode(type,"UTF8");
//            String data=URLEncoder.encode("type","UTF8")+"="+URLEncoder.encode((type),"UTF8");
//            String data= URLEncoder.encode("name","UTF8")+"="+ URLEncoder.encode(name,"UTF8");
            String data= URLEncoder.encode("itemid","UTF8")+"="+ URLEncoder.encode(itemid,"UTF8");
            data+="&"+  URLEncoder.encode("userid","UTF8")+"="+URLEncoder.encode(String.valueOf((userid)),"UTF8");


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

            ActivityShow.basket=builder.toString();

        }catch (Exception e){

        }
        return "";
    }
}
