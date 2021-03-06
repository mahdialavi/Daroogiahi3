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
 * Created by alavi on 2/15/2018.
 */

public class AsalAdapter extends RecyclerView.Adapter<AsalAdapter.HerosViewHolder> {

    ArrayList<Heros> asalArraylist =new ArrayList<>();


    public AsalAdapter(ArrayList<Heros> herosArrayList) {
        this.asalArraylist =herosArrayList;

    }

    @Override
    public AsalAdapter.HerosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.heros_recycle_rows,parent,false);
        return new AsalAdapter.HerosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AsalAdapter.HerosViewHolder holder, final int position) {

        final Heros heros= asalArraylist.get(position);
        holder.txtName.setText(heros.name);
        String getImg=heros.getImg();
        int img=G.context.getResources().getIdentifier(getImg,"drawable",G.context.getPackageName());
        holder.herosImg.setImageResource(R.drawable.asal);
        holder.linearLayout.setId(position);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=v.getId();
                String id2=heros.getid();
                String type="asal";
                String name = heros.getName();
                Intent intent=new Intent(G.context,ActivityShow.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id",id);
                intent.putExtra("type",type);
                intent.putExtra("name", name);
                intent.putExtra("id2",id2);

                G.context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return asalArraylist.size();
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
    }
}


