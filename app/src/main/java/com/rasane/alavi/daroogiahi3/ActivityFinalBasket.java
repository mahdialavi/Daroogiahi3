package com.rasane.alavi.daroogiahi3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Amin on 04/03/2018.
 */
public class ActivityFinalBasket extends ActivityEnhanced{

    public static String data="";

    public static BasketAdaptor adaptor;
    public static RecyclerView herosrecycle;
    LinearLayoutManager manager;
    LinearLayout linearCustomBasket;
    LinearLayout linearPay;
    TextView txtaddress;
    ArrayList<Heros> heros;
    public static SharedPreferences setting;
    LinearLayout.LayoutParams layoutParams;
    TextView txtTotalPrice;


    String title="";
    String finalprice="";
    String count="";
    String weight="";
    String idproduct="";
    String idbasket="";

    public static ArrayList<Heros> recycleInfos = new ArrayList<>();
    int totalPrice=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_basket);


        txtTotalPrice = (TextView) findViewById(R.id.txtTotalPrice);
        herosrecycle = (RecyclerView) findViewById(R.id.herosrecycle);
        Log.i("waitdeletbasket", ActivityFinalBasket.data);
        final Heros heros = new Heros(idbasket,count,title,idproduct,weight,finalprice);

            try {
            JSONArray jsonArray = new JSONArray(data);
            for (int i=0;i< jsonArray.length();i++) {

                JSONObject object=jsonArray.getJSONObject(i);
                heros.idbasket=object.getString("idbasket");
                heros.count=object.getString("count");
                heros.idproduct=object.getString("idproduct");
                heros.title=object.getString("title");
                heros.weight=object.getString("weight");
                heros.finalprice=object.getString("finalprice");
                totalPrice+=Integer.parseInt(heros.finalprice);
                recycleInfos.add(heros);
            } } catch (JSONException e) {
            e.printStackTrace(); }
        txtTotalPrice.setText(totalPrice+"");
            herosrecycle.setHasFixedSize(true);
        manager=new LinearLayoutManager(this);
        herosrecycle.setLayoutManager(manager);
        adaptor = new BasketAdaptor(recycleInfos);
        herosrecycle.setAdapter(adaptor);
        adaptor.notifyDataSetChanged();
        setting= PreferenceManager.getDefaultSharedPreferences(G.context);
        int userid=setting.getInt("user_id",0);
        txtTotalPrice=(TextView)findViewById(R.id.txtTotalPrice) ;
        txtaddress=(TextView)findViewById(R.id.txtaddress) ;
        txtaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(G.context,Activityaddres.class);
                intent.putExtra("Amount",totalPrice);
                intent.putExtra("Count",heros.count);
                intent.putExtra("Title",heros.title);
                intent.putExtra("IdBasket",heros.idbasket);
                intent.putExtra("IdProduct",heros.idproduct);
                intent.putExtra("Weight",heros.weight);
                startActivity(intent);
                }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityFinalBasket.data = "";
       G.startActivity(Activity_category.class,true);
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.i("on resume findl basket", "onresume final basket");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i("onstop", "onstopf finalbasket");
    }
    @Override
    protected void onPause() {
        super.onPause();
        ActivityFinalBasket.data = "";

        Log.i("onpause", "onpause activity_finalbasket");



    }
}

