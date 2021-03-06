package com.rasane.alavi.daroogiahi3;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by alavi on 2/11/2018.
 */

public class TeaAdapter extends RecyclerView.Adapter<TeaAdapter.HerosViewHolder> {

    ArrayList<Heros> teaArraylist =new ArrayList<>();


    public TeaAdapter(ArrayList<Heros> herosArrayList) {
        this.teaArraylist =herosArrayList;

    }

    @Override
    public HerosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.heros_recycle_rows,parent,false);
        return new HerosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HerosViewHolder holder, final int position) {

        final Heros heros= teaArraylist.get(position);
        holder.txtName.setText(heros.name);
        String getImg=heros.getImg();
        int img=G.context.getResources().getIdentifier(getImg,"drawable",G.context.getPackageName());

        holder.herosImg.setImageResource(R.drawable.tea);
        holder.linearLayout.setId(position);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=v.getId();
                String type="herbaltea";
                String name = heros.getName();
                Intent intent=new Intent(G.context,ActivityShow.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id",id);
                intent.putExtra("type",type);
                intent.putExtra("name", name);
                G.context.startActivity(intent);

//                Toast.makeText(G.context, "id= " + id +  "type = " + type + "", Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public int getItemCount() {

        return teaArraylist.size();
    }

    public class HerosViewHolder extends RecyclerView.ViewHolder{

        public ImageView herosImg;
        LinearLayout linearLayout;
        public TextView txtName;
        public HerosViewHolder(View itemView) {
            super(itemView);
            herosImg=(ImageView)itemView.findViewById(R.id.herosImg);
            txtName=(TextView)itemView.findViewById(R.id.txtHerosName);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.linear);
        }
    }}