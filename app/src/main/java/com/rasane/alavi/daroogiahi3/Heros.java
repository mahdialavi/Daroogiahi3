package com.rasane.alavi.daroogiahi3;

/**
 * Created by alavi on 2/11/2018.
 */

public class Heros {
    String id;
    public String name;
    public String type;
    public String img;
    public int fav;
    public String desc;


    public String idbasket;
    public String count;
    public String title;
    public String idproduct;
    public String weight;
    public String finalprice;


    public Heros(String idbasket, String count, String title, String idproduct, String weight, String finalprice) {
        this.idproduct=idproduct;
        this.idbasket=idbasket;
        this.count=count;
        this.title=title;
        this.weight=weight;
        this.finalprice=finalprice;

    }

    public Heros(String id, String name, String desc, String img, int fav, String type){
        this.id=id;
        this.name=name;
        this.desc=desc;
        this.img=img;
        this.fav=fav;
        this.type=type;

    }

    public String getid(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getImg(){

        return img;
    }
    public int getFav(){
        return fav;
    }
    public String getDesc(){
        return desc;
    }
    public String getType(){
        return type;
    }
}
