package com.rasane.alavi.daroogiahi3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.JsonReader;
import android.util.JsonToken;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.io.IOException;
import java.io.StringReader;


public class Payment extends AppCompatActivity {

    String LinkSend = "Your Address file send.php";

    WebView webView ;

    String Amount = "" ;
    String Description = "" ;
    String Email = "" ;
    String Mobile = "" ;
    String Mahsol = "" ;


//    Developer : Mohammad Mokhles ----- WebSite : http://smaartapp.ir ------------ Email : info@smaartapp.ir


    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        Amount = getIntent().getExtras().getString("Amount");
        Description = getIntent().getExtras().getString("Description");
        Email = getIntent().getExtras().getString("Email");
        Mobile = getIntent().getExtras().getString("Mobile");
        Mahsol = getIntent().getExtras().getString("Mahsol");

        webView = (WebView)findViewById(R.id.webView);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);

        webView.addJavascriptInterface(new JsClass(this),"HtmlViewer");

        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageFinished(WebView view, String url) {

                if(Build.VERSION.SDK_INT >= 19){

                    webView.evaluateJavascript("document.getElementsByTagName('html')[0].innerHTML", new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String value) {

                            JsonReader reader = new JsonReader(new StringReader(value));
                            reader.setLenient(true);

                            try {

                                if(reader.peek() != JsonToken.NULL){
                                    if(reader.peek() == JsonToken.STRING){
                                        String msg = reader.nextString();
                                        if(msg != null) {

                                            String result = Html.fromHtml(msg).toString();

                                            final String[] s = result.split(",");

                                            if(s[0].equals("NULL")){
                                                Toast.makeText(getApplicationContext(),"مقادیر ارسالی خالی مبباشد !",Toast.LENGTH_LONG).show();
                                                finish();
                                            }else if(s[0].equals("ERROR")){
                                                Toast.makeText(getApplicationContext(),"خطایی رخ داده است . کد خطا : "+s[1],Toast.LENGTH_LONG).show();
                                                finish();
                                            }else if(s[0].equals("CANCELL")){
                                                Toast.makeText(getApplicationContext(),"شما از خرید کالا منصرف شده اید ! انشالا سری بعد ...",Toast.LENGTH_LONG).show();
                                                finish();
                                            }else if(s[0].equals("OK")){
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
//                                                        MainActivity.txt_status.setText("پرداخت انجام شد . شماره تراکنش : "+s[1]);
                                                        Toast.makeText(getApplicationContext(),"پرداخت شما با موفقیت انجام شد . شماره تراکنش شما : "+s[1],Toast.LENGTH_LONG).show();
                                                        finish();
                                                    }
                                                });
                                            }else if(s[0].equals("OK1")){
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
//                                                        MainActivity.txt_status.setText("پرداخت شما با موفقیت صورت گرفت . محصول شماره "+"1"+"برای شما ثبت شد . شماره تراکنش شما برابر است با : "+s[1]);
                                                        Toast.makeText(getApplicationContext(),"پرداخت شما با موفقیت صورت گرفت . محصول شماره "+"1"+"برای شما ثبت شد . شماره تراکنش شما برابر است با : "+s[1],Toast.LENGTH_LONG).show();
                                                        finish();
                                                    }
                                                });
                                            }else if(s[0].equals("OK2")){
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                       // MainActivity.txt_status.setText("پرداخت شما با موفقیت صورت گرفت . محصول شماره "+"2"+"برای شما ثبت شد . شماره تراکنش شما برابر است با : "+s[1]);
                                                        Toast.makeText(getApplicationContext(),"پرداخت شما با موفقیت صورت گرفت . محصول شماره "+"2"+"برای شما ثبت شد . شماره تراکنش شما برابر است با : "+s[1],Toast.LENGTH_LONG).show();
                                                        finish();
                                                    }
                                                });
                                            }else if(s[0].equals("OK3")){
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
//                                                        MainActivity.txt_status.setText("پرداخت شما با موفقیت صورت گرفت . محصول شماره "+"3"+"برای شما ثبت شد . شماره تراکنش شما برابر است با : "+s[1]);
                                                        Toast.makeText(getApplicationContext(),"پرداخت شما با موفقیت صورت گرفت . محصول شماره "+"3"+"برای شما ثبت شد . شماره تراکنش شما برابر است با : "+s[1],Toast.LENGTH_LONG).show();
                                                        finish();
                                                    }
                                                });
                                            }else if(s[0].equals("OK4")){
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
//                                                        MainActivity.txt_status.setText("پرداخت شما با موفقیت صورت گرفت . محصول شماره "+"4"+"برای شما ثبت شد . شماره تراکنش شما برابر است با : "+s[1]);
                                                        Toast.makeText(getApplicationContext(),"پرداخت شما با موفقیت صورت گرفت . محصول شماره "+"4"+"برای شما ثبت شد . شماره تراکنش شما برابر است با : "+s[1],Toast.LENGTH_LONG).show();
                                                        finish();
                                                    }
                                                });
                                            }else if(s[0].equals("OK5")){
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
//                                                        MainActivity.txt_status.setText("پرداخت شما با موفقیت صورت گرفت . محصول شماره "+"5"+"برای شما ثبت شد . شماره تراکنش شما برابر است با : "+s[1]);
                                                        Toast.makeText(getApplicationContext(),"پرداخت شما با موفقیت صورت گرفت . محصول شماره "+"5"+"برای شما ثبت شد . شماره تراکنش شما برابر است با : "+s[1],Toast.LENGTH_LONG).show();
                                                        finish();
                                                    }
                                                });
                                            }

                                        }
                                    }
                                }
                            } catch (IOException e){
                                e.printStackTrace();
                            } finally {
                                try {
                                    reader.close();
                                }catch (IOException e){
                                    e.printStackTrace();
                                }
                            }
                        }
                    });

                }else {
                    webView.loadUrl("javascript:window.HtmlViewer.get(document.getElementsByTagName('html')[0].innerHTML);");
                }

            }


            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

        });


        webView.loadUrl(LinkSend+"?Amount="+Amount+"&Description="+Description+"&Email="+Email+"&Mobile="+Mobile+"&Mahsol="+Mahsol);
        Toast.makeText(getApplicationContext(),"صفحه در حال بارگذاری میباشد . لطفا صبر کنید ...",Toast.LENGTH_LONG).show();



    }

//    Developer : Mohammad Mokhles ----- WebSite : http://smaartapp.ir ------------ Email : info@smaartapp.ir

    class JsClass {

        Context context ;


        JsClass(Context c){
            this.context = c ;
        }

        public void get (String html){

            String result = Html.fromHtml(html).toString();

            final String[] s = result.split(",");

            if(s[0].equals("NULL")){
                Toast.makeText(context,"مقادیر ارسالی خالی مبباشد !",Toast.LENGTH_LONG).show();
                finish();
            }else if(s[0].equals("ERROR")){
                Toast.makeText(context,"خطایی رخ داده است . کد خطا : "+s[1],Toast.LENGTH_LONG).show();
                finish();
            }else if(s[0].equals("CANCELL")){
                Toast.makeText(context,"شما از خرید کالا منصرف شده اید ! انشالا سری بعد ...",Toast.LENGTH_LONG).show();
                finish();
            }else if(s[0].equals("OK")){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        MainActivity.txt_status.setText("پرداخت انجام شد . شماره تراکنش : "+s[1]);
                        Toast.makeText(context,"پرداخت شما با موفقیت انجام شد . شماره تراکنش شما : "+s[1],Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
            }else if(s[0].equals("OK1")){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        MainActivity.txt_status.setText("پرداخت شما با موفقیت صورت گرفت . محصول شماره "+"1"+"برای شما ثبت شد . شماره تراکنش شما برابر است با : "+s[1]);
                        Toast.makeText(context,"پرداخت شما با موفقیت صورت گرفت . محصول شماره "+"1"+"برای شما ثبت شد . شماره تراکنش شما برابر است با : "+s[1],Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
            }else if(s[0].equals("OK2")){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        MainActivity.txt_status.setText("پرداخت شما با موفقیت صورت گرفت . محصول شماره "+"2"+"برای شما ثبت شد . شماره تراکنش شما برابر است با : "+s[1]);
                        Toast.makeText(context,"پرداخت شما با موفقیت صورت گرفت . محصول شماره "+"2"+"برای شما ثبت شد . شماره تراکنش شما برابر است با : "+s[1],Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
            }else if(s[0].equals("OK3")){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        MainActivity.txt_status.setText("پرداخت شما با موفقیت صورت گرفت . محصول شماره "+"3"+"برای شما ثبت شد . شماره تراکنش شما برابر است با : "+s[1]);
                        Toast.makeText(context,"پرداخت شما با موفقیت صورت گرفت . محصول شماره "+"3"+"برای شما ثبت شد . شماره تراکنش شما برابر است با : "+s[1],Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
            }else if(s[0].equals("OK4")){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        MainActivity.txt_status.setText("پرداخت شما با موفقیت صورت گرفت . محصول شماره "+"4"+"برای شما ثبت شد . شماره تراکنش شما برابر است با : "+s[1]);
                        Toast.makeText(context,"پرداخت شما با موفقیت صورت گرفت . محصول شماره "+"4"+"برای شما ثبت شد . شماره تراکنش شما برابر است با : "+s[1],Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
            }else if(s[0].equals("OK5")){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        MainActivity.txt_status.setText("پرداخت شما با موفقیت صورت گرفت . محصول شماره "+"5"+"برای شما ثبت شد . شماره تراکنش شما برابر است با : "+s[1]);
                        Toast.makeText(context,"پرداخت شما با موفقیت صورت گرفت . محصول شماره "+"5"+"برای شما ثبت شد . شماره تراکنش شما برابر است با : "+s[1],Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
            }

        }


    }

//    Developer : Mohammad Mokhles ----- WebSite : http://smaartapp.ir ------------ Email : info@smaartapp.ir



}
