package com.rasane.alavi.daroogiahi3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

public class ActivityFruits extends AppCompatActivity {
    RecyclerView rvFruit;
    LinearLayoutManager manager;
    Fruitadapter fruitadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruits);

        manager = new LinearLayoutManager(G.context);

        rvFruit = (RecyclerView) findViewById(R.id.fruitRecycle);

        fruitadapter  = new Fruitadapter(Activity_category.ftuitArraylist);
        rvFruit.setLayoutManager(manager);
        rvFruit.setHasFixedSize(true);
        rvFruit.setAdapter(fruitadapter);
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
                fruitadapter.notifyDataSetChanged();
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
        Activity_category.ftuitArraylist.clear();
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String desc = cursor.getString(cursor.getColumnIndex("desc"));
            String pic = cursor.getString(cursor.getColumnIndex("pic"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            int fav = cursor.getInt(cursor.getColumnIndex("fav"));
            Heros heros = new Heros(id, name, desc, pic, fav, type);
            Activity_category.ftuitArraylist.add(heros);
        }}
    @Override
    public  void onBackPressed() {
        finish();    }
}
