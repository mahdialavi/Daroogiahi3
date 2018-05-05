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

public class AraghAdapter extends RecyclerView.Adapter<AraghAdapter.HerosViewHolder> {

    ArrayList<Heros> araghiyatArraylist =new ArrayList<>();


    public AraghAdapter(ArrayList<Heros> herosArrayList) {
        this.araghiyatArraylist =herosArrayList;

    }
    @Override
    public AraghAdapter.HerosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.heros_recycle_rows,parent,false);
        return new AraghAdapter.HerosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AraghAdapter.HerosViewHolder holder, final int position) {

        final Heros heros= araghiyatArraylist.get(position);
        holder.txtName.setText(heros.name);
        String getImg=heros.getImg();
        int img=G.context.getResources().getIdentifier(getImg,"drawable",G.context.getPackageName());
        holder.herosImg.setImageResource(R.drawable.aragh);
        holder.linearLayout.setId(position);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=v.getId();
                String id2=heros.getid();
                String name = heros.getName();
                String type="aragh";
                Intent intent=new Intent(G.context,ActivityShow.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id",id);
                intent.putExtra("type",type);
                intent.putExtra("id2",id2);
                intent.putExtra("name", name);
                G.context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return araghiyatArraylist.size();
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


