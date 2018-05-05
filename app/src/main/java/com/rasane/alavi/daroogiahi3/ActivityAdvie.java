package com.rasane.alavi.daroogiahi3;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

public class ActivityAdvie extends ActivityEnhanced{
    RecyclerView rvadvie;
    LinearLayoutManager manager;
    AdvieAdapter advieAdapter;
    private boolean exit = false;
    SharedPreferences setting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advie);


        manager = new LinearLayoutManager(G.context);
        rvadvie = (RecyclerView) findViewById(R.id.AdvieRecycle);
        advieAdapter  = new AdvieAdapter(Activity_category.advieArraylist);
        rvadvie.setLayoutManager(manager);
        rvadvie.setHasFixedSize(true);
        rvadvie.setAdapter(advieAdapter);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        EditText edtSearch;
        edtSearch = (EditText) findViewById(R.id.edtSearch);
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                search(s.toString());
                advieAdapter.notifyDataSetChanged();
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
    public static void search(CharSequence charSequence) {
        SQLiteDatabase database;
        database = SQLiteDatabase.openOrCreateDatabase(G.direction + "/material_book.sqlite", null);
        Cursor cursor = database.rawQuery("SELECT * FROM tbl_heros WHERE name LIKE '%" + charSequence + "%'", null);
        Activity_category.advieArraylist.clear();
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String desc = cursor.getString(cursor.getColumnIndex("desc"));
            String pic = cursor.getString(cursor.getColumnIndex("pic"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            int fav = cursor.getInt(cursor.getColumnIndex("fav"));
//            Heros heros = new Heros(id, name, desc, pic, fav, type);
//            Activity_category.advieArraylist.add(heros);
        }}
    @Override
    public  void onBackPressed() {
finish();
    }
    }




