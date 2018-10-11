package com.ron.bonus.alibonus.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import static android.content.Context.CLIPBOARD_SERVICE;

public class BonusUtil {


    public static String getSystemClipText(Context context){
        final android.content.ClipboardManager clipboardManager = (ClipboardManager)context.getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = clipboardManager.getPrimaryClip();
        int itemCount = clipData.getItemCount();
        if(itemCount > 0) {
            // Get source text.
            ClipData.Item item = clipData.getItemAt(0);
               String text = item.getText().toString();
            return text;
        }
        return null;
    }


    public static void saveSystemClip(Context context, String label, String text){
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(label, text);
        clipboard.setPrimaryClip(clip);
    }


}
