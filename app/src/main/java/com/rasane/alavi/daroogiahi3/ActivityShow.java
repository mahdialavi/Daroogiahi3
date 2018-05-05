package com.rasane.alavi.daroogiahi3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class ActivityShow extends ActivityEnhanced {

    public static String data="";
    public static String basket="";
    public static String info="";

    String itemid;
    String id;
    int linearId;
    String sendType="";
    public String name="";
    public String type="";
    public String img;
    public int fav;
    public String desc;
    TextView txtHerosdesc;
    TextView txttitle;
    ImageView imgShare;
    ImageView imagetoolbar;


    int userid ;

    public static SharedPreferences setting;
    TextView txt_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        setting = PreferenceManager.getDefaultSharedPreferences(G.context);




        LinearLayout linearFinalBasket=(LinearLayout)findViewById(R.id.linearFilnalBasket);
        linearFinalBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//             G.startActivity(ActivityWaitBasket.class,false);
                Intent intent = new Intent(G.context,ActivityWaitBasket.class);
                startActivity(intent);
            }
        });
        txtHerosdesc = (TextView) findViewById(R.id.txtHerosdesc);
        txttitle = (TextView) findViewById(R.id.txtTitle);
        imgShare = (ImageView) findViewById(R.id.imgshare);
//        txt_user=(TextView)findViewById(R.id.txt_user);
 imagetoolbar = (ImageView) findViewById(R.id.imagetoolbar);
        imagetoolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        SharedPreferences.Editor editor=setting.edit();
                        editor.remove("user_id");
                        editor.commit();
                        Intent i=  new Intent(G.context,MainActivity.class);
                        startActivity(i);
                        ActivityShow.this.finish();
            }
        });
//   findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressed();
//            }
//        });
        Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                linearId = (bundle.getInt("id"));
                itemid = (bundle.getString("id2"));
                sendType=(bundle.getString("type"));
                name = (bundle.getString("name"));
//                Toast.makeText(G.context,itemid+"", Toast.LENGTH_SHORT).show();
            }
        if(sendType.equals("advie")){
                id = Activity_category.advieArraylist.get(linearId).getid();
                name = Activity_category.advieArraylist.get(linearId).getName();
                type = Activity_category.advieArraylist.get(linearId).getType();
                img = Activity_category.advieArraylist.get(linearId).getImg();
                fav = Activity_category.advieArraylist.get(linearId).getFav();
                desc = Activity_category.advieArraylist.get(linearId).getDesc();
                txtHerosdesc.setText(desc);
                txttitle.setText(name);}
            else if(sendType.equals("damnoosh")){
                id = Activity_category.damnooshArraylist.get(linearId).getid();
                name = Activity_category.damnooshArraylist.get(linearId).getName();
                type = Activity_category.damnooshArraylist.get(linearId).getType();
                img = Activity_category.damnooshArraylist.get(linearId).getImg();
                fav = Activity_category.damnooshArraylist.get(linearId).getFav();
                desc = Activity_category.damnooshArraylist.get(linearId).getDesc();
                txtHerosdesc.setText(desc);
                txttitle.setText(name);
        }else if(sendType.equals("asal")){
                id = Activity_category.asalArraylist.get(linearId).getid();
                name = Activity_category.asalArraylist.get(linearId).getName();
                type = Activity_category.asalArraylist.get(linearId).getType();
                img = Activity_category.asalArraylist.get(linearId).getImg();
                fav = Activity_category.asalArraylist.get(linearId).getFav();
                desc = Activity_category.asalArraylist.get(linearId).getDesc();
                txtHerosdesc.setText(desc);
                txttitle.setText(name);
        }else if(sendType.equals("aragh")){
                id = Activity_category.araghiyatArraylist.get(linearId).getid();
                name = Activity_category.araghiyatArraylist.get(linearId).getName();
                type = Activity_category.araghiyatArraylist.get(linearId).getType();
                img = Activity_category.araghiyatArraylist.get(linearId).getImg();
                fav = Activity_category.araghiyatArraylist.get(linearId).getFav();
                desc = Activity_category.araghiyatArraylist.get(linearId).getDesc();
                txtHerosdesc.setText(desc);
                txttitle.setText(name);
        } else if(sendType.equals("fruit")){
                id = Activity_category.ftuitArraylist.get(linearId).getid();
                name = Activity_category.ftuitArraylist.get(linearId).getName();
                type = Activity_category.ftuitArraylist.get(linearId).getType();
                img = Activity_category.ftuitArraylist.get(linearId).getImg();
                fav = Activity_category.ftuitArraylist.get(linearId).getFav();
                desc = Activity_category.ftuitArraylist.get(linearId).getDesc();
                txtHerosdesc.setText(desc);
                txttitle.setText(name);
        }
            else if(sendType.equals("herbaltea")){
                id = Activity_category.teaArraylist.get(linearId).getid();
                name = Activity_category.teaArraylist.get(linearId).getName();
                type = Activity_category.teaArraylist.get(linearId).getType();
                img = Activity_category.teaArraylist.get(linearId).getImg();
                fav = Activity_category.teaArraylist.get(linearId).getFav();
                desc = Activity_category.teaArraylist.get(linearId).getDesc();
                txtHerosdesc.setText(desc);
                txttitle.setText(name);
        }
        findViewById(R.id.linearAddBasket).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if (OnlineCheck.isOnline()) {

                new AsyncTaskReadBasket("http://192.168.1.102/daroogiahi-2/daroows/getinfo.php", itemid).execute();
                    final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(ActivityShow.this);
                    View parent = getLayoutInflater().inflate(R.layout.dialog, null);

                    final TextView txtProductTitle = (TextView) parent.findViewById(R.id.txtProductTitle);
                    final TextView txtProductPrice = (TextView) parent.findViewById(R.id.txtprice);
                    final TextView txtProductweight = (TextView) parent.findViewById(R.id.txtweight);
                    final Button btnAddBasket = (Button) parent.findViewById(R.id.btnAddBasket);
                    txtProductTitle.setText(name);

                    final Timer timer = new Timer();
                    timer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (!ActivityShow.info.equals("")) {
                                        Toast.makeText(G.context, info, Toast.LENGTH_LONG).show();

                                        try {
                                            JSONArray jsonarray = new JSONArray(info);
                                            for (int i = 0; i <= jsonarray.length(); i++) {
                                                JSONObject jsonobject = jsonarray.getJSONObject(i);
                                                String price = jsonobject.getString("price");
                                                String weight = jsonobject.getString("weight");
                                                txtProductPrice.setText(price);
                                                txtProductweight.setText(weight);
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        timer.cancel();
                                    }
                                }
                            });
                        }
                    }, 1, 1000);
                    userid = setting.getInt("user_id", 0);
                    btnAddBasket.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (setting.getInt("user_id", 0) == 0) {//not login
                                Intent i = new Intent(ActivityShow.this, LoginActivity.class);
                                startActivity(i);
                                ActivityShow.this.finish();
                            } else {//login ok

//                            txt_user.setText("User id = " + setting.getInt("user_id", 0));
                                new AsyncTaskAddToBasket("http://192.168.1.102/daroogiahi-2/daroows/addbasket.php", userid, itemid).execute();
                                final Timer timer = new Timer();
                                timer.scheduleAtFixedRate(new TimerTask() {
                                    @Override
                                    public void run() {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                if (!ActivityShow.basket.equals("")) {
                                                    Toast.makeText(G.context, basket, Toast.LENGTH_SHORT).show();
                                                    timer.cancel();
                                                    bottomSheetDialog.dismiss();
                                                }}
                                        });
                                    }
                                }, 1, 1000);}
                        }
                    });

                    bottomSheetDialog.setContentView(parent);
                    BottomSheetBehavior<View> bottomSheetBehavior = BottomSheetBehavior.from((View) parent.getParent());
                    bottomSheetBehavior.setPeekHeight(
                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 400, getResources().getDisplayMetrics())

                    );
                    bottomSheetDialog.show();
//                } else {
//                    Toast.makeText(G.context,"لطفا اتصال دستگاه به اینترنت را چک کنید!",Toast.LENGTH_LONG).show();
//                }
            }
        });
        imgShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT,desc);
                    intent.putExtra(Intent.EXTRA_SUBJECT,name);
                    startActivity(Intent.createChooser(intent,"اشتراک گذاری"));
                }
            });

        }

@Override
public  void onBackPressed() {

    finish();
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.i("on resume", "onresume");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("onstop", "onstop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("onpause", "onpause");

    }
}

