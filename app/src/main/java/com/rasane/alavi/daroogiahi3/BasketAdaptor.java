package com.rasane.alavi.daroogiahi3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Amin on 10/03/2018.
 */

public class BasketAdaptor extends RecyclerView.Adapter<viewHolder> {
   public static ArrayList<Heros> recycleInfos=new ArrayList<>();
    public BasketAdaptor(ArrayList<Heros> recycleInfos){
        this.recycleInfos=recycleInfos;
    }
    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.basketadaptor,parent,false);
        return new viewHolder(view);
    }

    @Override
    public int getItemCount() {
        return recycleInfos.size();
    }

     @Override
    public void onBindViewHolder(viewHolder holder, final int position) {
         Heros heros=recycleInfos.get(position);

        holder.titleFinalBasket.setText(heros.title);
        holder.countFinalBasket.setText(heros.count);
        holder.weightFinalBasket.setText(heros.weight);
         holder.totalFinalBasket.setText(heros.finalprice);

        final String idproduct= heros.idproduct;


         final int currentposition=position;
         final Heros hero = recycleInfos.get(position);


         holder.deleteFinalBasket.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 SharedPreferences setting = PreferenceManager.getDefaultSharedPreferences(G.context);
                 int userid=setting.getInt("user_id",0);
                 if (OnlineCheck.isOnline()) {
                     new AsyncTaskDeleteBasket("http://192.168.1.201/daroogiahi-2/daroows/deletebasket.php", idproduct, userid).execute();

                     removeitem(hero);
                     Toast.makeText(G.context, "محصول با موفقیت از سبد خرید حذف گردید !", Toast.LENGTH_LONG).show();
                 } else {
                     Toast.makeText(G.context, "حذف محصول با مشکل مواجه گردید ! ", Toast.LENGTH_LONG).show();
                     }

//                Intent intent=new Intent(G.context,Activityaddres.class);
//                 intent.putExtra("userid",userid);
//                 intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                 G.context.startActivity(intent);
             }

             
         });

         holder.img.setOnLongClickListener(new View.OnLongClickListener() {
             @Override
             public boolean onLongClick(View v) {


                 additem(currentposition,hero);
                 return true;


             }
         });

         //        Picasso.with(G.context).load("http://192.168.1.101/digikala/img/"+recycleInfo.img).into(holder.img);
     }



    private void removeitem(Heros hero) {

        int currentpostion = recycleInfos.indexOf(hero);
        recycleInfos.remove(currentpostion);
        notifyItemRemoved(currentpostion);

    }

    private void additem(int position, Heros hero) {
        recycleInfos.add(position, hero);
        notifyItemInserted(position);
    }

}
class viewHolder extends RecyclerView.ViewHolder{

    public ImageView img;
    public TextView txtName;
    public TextView titleFinalBasket;
    public TextView countFinalBasket;
    public TextView totalFinalBasket;
    public TextView weightFinalBasket;
    public TextView deleteFinalBasket;

    public LinearLayout linearfinalbasket;

    public viewHolder(View itemView) {
        super(itemView);

        img=(ImageView)itemView.findViewById(R.id.imgFinalBasket);
        titleFinalBasket=(TextView)itemView.findViewById(R.id.titleFinalBasket);
        weightFinalBasket=(TextView)itemView.findViewById(R.id.weightFinalBasket);
        countFinalBasket=(TextView)itemView.findViewById(R.id.countFinalBasket);
        totalFinalBasket=(TextView)itemView.findViewById(R.id.totalFinalBasket);
        deleteFinalBasket=(TextView)itemView.findViewById(R.id.deleteFinalBasket);


    }
}
