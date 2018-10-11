package com.ron.bonus.alibonus.model;

import android.content.Context;

import com.ron.bonus.alibonus.util.FileUtil;

public class BonusDb {

    private static final String TAG = "BonusDb";

    private static final String FILE_NAME = "bonus.json";

    public static BonusData loadFromData(Context context){
        BonusData bonusData = new BonusData();
        String content = FileUtil.loadData(context, FILE_NAME);

        if(null!=content){
            bonusData = BonusData.fromJson(content);
        }

        return bonusData;
    }


    public static int saveToData(Context context, BonusData bonusData){
        return FileUtil.saveData(context, FILE_NAME, bonusData.toJson());
    }

}
