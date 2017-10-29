package com.aprigoldcorporation.allculator.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aprigoldcorporation.allculator.R;
import com.aprigoldcorporation.allculator.models.CurrencyAdapterModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by musta on 2.09.2017.
 */

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyHolder> {

    List<CurrencyAdapterModel> recentCurrencies;

    public CurrencyAdapter() {
        recentCurrencies = new ArrayList<>();
    }

    public void addRecent(CurrencyAdapterModel model){
        recentCurrencies.add(model);
        this.notifyDataSetChanged();
    }

    @Override
    public CurrencyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.currency_listview,parent,false);
        return new CurrencyHolder(v);
    }

    @Override
    public void onBindViewHolder(CurrencyHolder holder, int position) {
        CurrencyAdapterModel model = recentCurrencies.get(position);
        holder.first_txt.setText(model.getTxt_first());
        holder.second_txt.setText(model.getTxt_second());
        holder.firs_number.setText(model.getTxt_num1());
        holder.second_number.setText(model.getTxt_num2());
    }

    @Override
    public int getItemCount() {
        return recentCurrencies.size();
    }

    public class CurrencyHolder extends RecyclerView.ViewHolder {
        TextView first_txt, second_txt, firs_number, second_number;

        public CurrencyHolder(View itemView) {
            super(itemView);
            first_txt= (TextView) itemView.findViewById(R.id.first_currency_text);
            second_txt= (TextView) itemView.findViewById(R.id.second_currency_text);
            firs_number = (TextView) itemView.findViewById(R.id.first_currency_number);
            second_number = (TextView) itemView.findViewById(R.id.second_currency_number);
        }

    }
}
