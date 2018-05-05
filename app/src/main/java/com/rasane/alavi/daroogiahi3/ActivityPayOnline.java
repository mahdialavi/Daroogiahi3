package com.rasane.alavi.daroogiahi3;

import android.annotation.SuppressLint;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.Html;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * Created by Amin on 05/03/2018.
 */

public class ActivityPayOnline extends ActivityEnhanced {

    WebView webView;
    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_online);
        webView=(WebView)findViewById(R.id.webView);
        String price=getIntent().getExtras().getString("price");

        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new Js(),"cc");
        webView.getSettings().setDomStorageEnabled(true);


        webView.setWebViewClient(new WebViewClient(){

            @Override
            @SuppressLint("JavascriptInterface")
            @JavascriptInterface
            public void onPageFinished(WebView view, String url) {
                webView.loadUrl("javascript:window.cc.show(document.getElementsByTagName('html')[0].innerHTML);");

            }


            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });

        webView.loadUrl("http://www.program-learning.com/mellat/example.php?price="+price);
        }

    class Js{
        @JavascriptInterface
        public void show(String content){
            String code= Html.fromHtml(content).toString();
            code=code.trim();
            long convertCode=Long.parseLong(code);

            //Toast.makeText(G.context,code,Toast.LENGTH_SHORT).show();

            if(convertCode>0){
                Toast.makeText(G.context,"تراکنش موفقیت آمیز با شماره تراکنش "+convertCode,Toast.LENGTH_SHORT).show();
                finish();
                }

        }

    }
}
