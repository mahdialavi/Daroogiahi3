package com.rasane.alavi.daroogiahi3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity_category extends ActivityEnhanced{

    public static  String basket="";

    public static SQLiteDatabase database;
    public  static ArrayList<Heros> advieArraylist =new ArrayList<>();
    public  static ArrayList<Heros> araghiyatArraylist =new ArrayList<>();
    public  static ArrayList<Heros> ftuitArraylist =new ArrayList<>();
    public   static ArrayList<Heros> asalArraylist =new ArrayList<>();
    public  static ArrayList<Heros> damnooshArraylist =new ArrayList<>();
    public  static ArrayList<Heros> teaArraylist =new ArrayList<>();

    TextView txtBasketCount;
    LinearLayout linearFinalBasket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        txtBasketCount=(TextView)findViewById(R.id.txtBasketCont);

        linearFinalBasket=(LinearLayout)findViewById(R.id.linearFilnalBasket);
        linearFinalBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//             G.startActivity(ActivityWaitBasket.class,false);
                Intent intent = new Intent(G.context,ActivityWaitBasket.class);
                startActivity(intent);
            }
        });

        if(basket.equals("not exist")){

            txtBasketCount.setVisibility(View.GONE);
        }else{
            txtBasketCount.setText(basket);
        }

        findViewById(R.id.txtAdvie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             G.startActivity(ActivityAdvie.class,false);            }
        });

        findViewById(R.id.txtaraghiat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                G.startActivity(ActivityAraghiyat.class,false);
            }
        });
        findViewById(R.id.txtasal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                G.startActivity(ActivityAsal.class,false);
            }
        });
        findViewById(R.id.txtfruits).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                G.startActivity(ActivityFruits.class,false);
            }
        });
        findViewById(R.id.txtdamnoosh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(G.context,"damnoosh",Toast.LENGTH_SHORT).show();
            }
        }); findViewById(R.id.txttea).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                G.startActivity(ActivityTea.class,false);
            }
        });
//        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressed();
//            }
//        });

        selectAdvie();
        selectDamnoosh();
        selectAraghiyat();
        selectAsal();
        selectFruit();
        selectTea();
    }
    public static void selectAdvie(){
        database= SQLiteDatabase.openOrCreateDatabase(G.direction +"/material_book.sqlite",null);
        Cursor cursor=database.rawQuery("SELECT * FROM tbl_heros WHERE type='advie'",null);

        while(cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String name=cursor.getString(cursor.getColumnIndex("name"));
            String desc=cursor.getString(cursor.getColumnIndex("desc"));
            String pic=cursor.getString(cursor.getColumnIndex("pic"));
            String type=cursor.getString(cursor.getColumnIndex("type"));
            int fav=cursor.getInt(cursor.getColumnIndex("fav"));
            Heros heros=new Heros(id,name,desc,pic,fav,type);
            advieArraylist.add(heros);
        }
    }
    public static void selectTea(){
        database= SQLiteDatabase.openOrCreateDatabase(G.direction +"/material_book.sqlite",null);
        Cursor cursor=database.rawQuery("SELECT * FROM tbl_heros WHERE type='herbaltea'",null);
        while(cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String name=cursor.getString(cursor.getColumnIndex("name"));
            String desc=cursor.getString(cursor.getColumnIndex("desc"));
            String pic=cursor.getString(cursor.getColumnIndex("pic"));
            String type=cursor.getString(cursor.getColumnIndex("type"));
            int fav=cursor.getInt(cursor.getColumnIndex("fav"));
            Heros heros=new Heros(id,name,desc,pic,fav,type);
            teaArraylist.add(heros);
//            Toast.makeText(G.context,id+"",Toast.LENGTH_SHORT).show();


        }

    }


    public static void selectAsal(){

        database= SQLiteDatabase.openOrCreateDatabase(G.direction +"/material_book.sqlite",null);
        Cursor cursor=database.rawQuery("SELECT * FROM tbl_heros WHERE type='asal'",null);

        while(cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String name=cursor.getString(cursor.getColumnIndex("name"));
            String desc=cursor.getString(cursor.getColumnIndex("desc"));
            String pic=cursor.getString(cursor.getColumnIndex("pic"));
            String type=cursor.getString(cursor.getColumnIndex("type"));
            int fav=cursor.getInt(cursor.getColumnIndex("fav"));
            Heros heros=new Heros(id,name,desc,pic,fav,type);
            asalArraylist.add(heros);
        }
    }

    public static void selectDamnoosh(){

        database= SQLiteDatabase.openOrCreateDatabase(G.direction +"/material_book.sqlite",null);
        Cursor cursor=database.rawQuery("SELECT * FROM tbl_heros WHERE type='damnoosh'",null);

        while(cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String name=cursor.getString(cursor.getColumnIndex("name"));
            String desc=cursor.getString(cursor.getColumnIndex("desc"));
            String pic=cursor.getString(cursor.getColumnIndex("pic"));
            String type=cursor.getString(cursor.getColumnIndex("type"));
            int fav=cursor.getInt(cursor.getColumnIndex("fav"));
            Heros heros=new Heros(id,name,desc,pic,fav,type);
            damnooshArraylist.add(heros);
        }}

    public static void selectFruit(){

        database= SQLiteDatabase.openOrCreateDatabase(G.direction +"/material_book.sqlite",null);
        Cursor cursor=database.rawQuery("SELECT * FROM tbl_heros WHERE type='fruit'",null);

        while(cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String name=cursor.getString(cursor.getColumnIndex("name"));
            String desc=cursor.getString(cursor.getColumnIndex("desc"));
            String pic=cursor.getString(cursor.getColumnIndex("pic"));
            String type=cursor.getString(cursor.getColumnIndex("type"));
            int fav=cursor.getInt(cursor.getColumnIndex("fav"));
            Heros heros=new Heros(id,name,desc,pic,fav,type);
            ftuitArraylist.add(heros);
        }

    }

    public static void selectAraghiyat(){
        database= SQLiteDatabase.openOrCreateDatabase(G.direction +"/material_book.sqlite",null);
        Cursor cursor=database.rawQuery("SELECT * FROM tbl_heros WHERE type='aragh'",null);
        while(cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String name=cursor.getString(cursor.getColumnIndex("name"));
            String desc=cursor.getString(cursor.getColumnIndex("desc"));
            String pic=cursor.getString(cursor.getColumnIndex("pic"));
            String type=cursor.getString(cursor.getColumnIndex("type"));
            int fav=cursor.getInt(cursor.getColumnIndex("fav"));
            Heros heros=new Heros(id,name,desc,pic,fav,type);
            araghiyatArraylist.add(heros);
        }
    }
    @Override
    public void onBackPressed() {
finish();
    }
}
