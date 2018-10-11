package com.ron.bonus.alibonus.util;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileUtil {

    private static final String TAG = "FileUtil";

    public static String loadData(Context context, String inFile) {

        File file = new File(context.getFilesDir(), inFile);
        if (!file.exists()) {
            Log.w(TAG, inFile +" doesn't exists");
            return null;
        }

        StringBuilder buf = new StringBuilder();
        String str;
        BufferedReader in = null;
        try {

            FileInputStream json = new FileInputStream(file);
            in = new BufferedReader(new InputStreamReader(json, "UTF-8"));
            while ((str = in.readLine()) != null) {
                buf.append(str);
            }
            in.close();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        } finally {
            close(in);
        }
        return buf.toString();
    }


    public static int saveData(Context context, String filename, String fileContent) {
        int ret = -1;
        File file = new File(context.getFilesDir(), filename);

        FileOutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream(file);
            outputStream.write(fileContent.getBytes());
            ret = 0;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(outputStream);
        }
        return ret;

    }

    public static void close(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
