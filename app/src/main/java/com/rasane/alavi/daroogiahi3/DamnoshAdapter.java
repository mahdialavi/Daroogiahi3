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

public class DamnoshAdapter extends RecyclerView.Adapter<DamnoshAdapter.HerosViewHolder> {

    ArrayList<Heros> damnooshArraylist =new ArrayList<>();


    public DamnoshAdapter(ArrayList<Heros> herosArrayList) {
        this.damnooshArraylist =herosArrayList;

    }

    @Override
    public DamnoshAdapter.HerosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.heros_recycle_rows,parent,false);
        return new DamnoshAdapter.HerosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DamnoshAdapter.HerosViewHolder holder, final int position) {

        final Heros heros= damnooshArraylist.get(position);
        holder.txtName.setText(heros.name);
        String getImg=heros.getImg();
        int img=G.context.getResources().getIdentifier(getImg,"drawable",G.context.getPackageName());
        holder.herosImg.setImageResource(R.drawable.damnoosh);
//        Log.i("LOG","get Image: "+getImg+"/ img:"+img);


        holder.linearLayout.setId(position);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=v.getId();
                String type="damnoosh";
                String name = heros.getName();
                String id2=heros.getid();

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

        return damnooshArraylist.size();
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


