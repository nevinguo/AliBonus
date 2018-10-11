package com.ron.bonus.alibonus.model;

import android.text.TextUtils;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class BonusData {

    ArrayList<BonusItem> bonusList = new ArrayList<BonusItem>();


    public BonusData(){

    }

    public BonusItem get(int index){
        if(index< bonusList.size()){
            return bonusList.get(index);
        }
        return null;
    }

    public boolean contains(String bonus){
        for(BonusItem item: bonusList){
            if(TextUtils.equals(item.getBonus(), bonus)){
                return true;
            }
        }
        return false;
    }


    public int size(){
        return bonusList.size();
    }

    public List<BonusItem> getAllItem(){
        return new ArrayList<>(bonusList);
    }



    public void add(BonusItem item){
        bonusList.add(item);
    }

    public static BonusData fromJson(String json){
        return new Gson().fromJson(json, BonusData.class);
    }


    public String toJson(){
        return new Gson().toJson(this);
    }

}
