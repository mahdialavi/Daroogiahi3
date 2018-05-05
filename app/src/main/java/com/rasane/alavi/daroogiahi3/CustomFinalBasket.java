package com.rasane.alavi.daroogiahi3;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Amin on 04/03/2018.
 */

public class CustomFinalBasket extends LinearLayout {
    public TextView titleBasket;
    public String idproduct;
    public int userid;
    public ImageView imgBasket;
    public  TextView colorBasket;
    public  TextView gauranteeBasket;
    public  TextView countBasket;
    public  TextView totalBasket;
    public  TextView finalBasket;
    public  TextView deleteBasket;

    //    Activity activity = (Activity)getContext();
    public CustomFinalBasket(Context context) {
        super(context);
        init(context);
    }
    public CustomFinalBasket(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    public CustomFinalBasket(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.custom_final_basket,this,true);
        titleBasket=(TextView)view.findViewById(R.id.titleCustomFinalBasket);
        imgBasket=(ImageView) view.findViewById(R.id.imgCustomFinalBasket);
        colorBasket=(TextView)view.findViewById(R.id.colorCustomFinalBasket);

        countBasket=(TextView)view.findViewById(R.id.countCustomFinalBasket);
        totalBasket=(TextView)view.findViewById(R.id.totalCustomFinalBasket);
        finalBasket=(TextView)view.findViewById(R.id.finalCustomFinalBasket);
        deleteBasket=(TextView)view.findViewById(R.id.deleteCustomFinalBasket);

        deleteBasket.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(G.context,Activityaddres.class);
                intent.putExtra("idproduct",idproduct);
                intent.putExtra("userid",userid);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                G.context.startActivity(intent);


//                ActivityWaitBasket.data = "";

//                Toast.makeText(G.context,ActivityWaitBasket.data+"   "+userid,Toast.LENGTH_SHORT).show();


            }
        });

    }

}
