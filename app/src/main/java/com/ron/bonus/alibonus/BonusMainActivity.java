package com.ron.bonus.alibonus;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;

import com.ron.bonus.alibonus.model.BonusData;
import com.ron.bonus.alibonus.model.BonusDb;
import com.ron.bonus.alibonus.model.BonusItem;
import com.ron.bonus.alibonus.util.BonusUtil;

public class BonusMainActivity extends AppCompatActivity {

    private static final String TAG = "BonusMainActivity";

    private BonusData mBonusData = null;
    private RecyclerView mRecyclerView;

    private BonusAdapter mRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonus_main);

        mBonusData = BonusDb.loadFromData(this);

        mRecyclerView = findViewById(R.id.my_recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerAdapter = new BonusAdapter(this, mBonusData.getAllItem());
        mRecyclerView.setAdapter(mRecyclerAdapter);



        String clipText = BonusUtil.getSystemClipText(this);
        if (!TextUtils.isEmpty(clipText) && (!mBonusData.contains(clipText))) {
            showSaveDialog(null, clipText);
        }

        if (mBonusData.size() > 0) {
            BonusItem item = mBonusData.get(0);
            if (null != item) {
                showAliPayDialog(item.getLabel(), item.getBonus());
            }
        }

    }


    private void showSaveDialog(final String tag, final String text) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        builder.setTitle("Save entry")
                .setMessage("Save Clip data?\n" + text)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        saveClipText(tag, text);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


    private void showAliPayDialog(final String tag, final String text) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        builder.setTitle("Go to AliPay")
                .setMessage("Go to AliPay?\n" + text)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        gotoAliPay(tag, text);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void saveClipText(final String tag, final String text) {
        BonusItem item = new BonusItem(text, tag);
        if (null == mBonusData) {
            mBonusData = new BonusData();
        }
        mBonusData.add(item);
        BonusDb.saveToData(this, mBonusData);

        mRecyclerAdapter.updateData(mBonusData.getAllItem());

    }


    private void gotoAliPay(String tag, String text) {
        BonusUtil.saveSystemClip(this, tag, text);

        try {
            PackageManager packageManager
                    = this.getApplicationContext().getPackageManager();
            Intent intent = packageManager.
                    getLaunchIntentForPackage("com.eg.android.AlipayGphone");
            startActivity(intent);
            this.finish();
        } catch (Exception e) {
            Log.e(TAG, "gotoAliPay error", e);
        }

    }

}
