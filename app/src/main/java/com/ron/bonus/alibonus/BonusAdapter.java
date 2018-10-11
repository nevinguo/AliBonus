package com.ron.bonus.alibonus;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ron.bonus.alibonus.model.BonusItem;

import java.util.List;

public class BonusAdapter extends RecyclerView.Adapter<BonusAdapter.MyViewHolder> {



    private List<BonusItem> mDatas;
    private Context mContext;
    private LayoutInflater inflater;

    public BonusAdapter(Context context, List<BonusItem> datas) {
        this.mContext = context;
        this.mDatas = datas;
        inflater = LayoutInflater.from(mContext);
    }

    public void updateData(List<BonusItem> datas){
        this.mDatas = datas;
        this.notifyDataSetChanged();
    }



    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        BonusItem item = mDatas.get(position);
        holder.bonusLabel.setText(item.getLabel());
        holder.bonusValue.setText(item.getBonus());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.bonus_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView bonusLabel;
        TextView bonusValue;

        public MyViewHolder(View view) {
            super(view);
            bonusLabel = (TextView) view.findViewById(R.id.bonus_label);
            bonusValue = (TextView) view.findViewById(R.id.bonus_value);
        }

    }
}

