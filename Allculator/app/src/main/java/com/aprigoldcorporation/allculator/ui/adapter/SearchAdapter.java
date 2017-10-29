package com.aprigoldcorporation.allculator.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.aprigoldcorporation.allculator.R;

import java.util.List;

/**
 * Created by musta on 30.08.2017.
 */

public class SearchAdapter extends ArrayAdapter<String> {

    public SearchAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public SearchAdapter(Context context, int resource, List<String> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.search_list_row, null);
        }

        String p = getItem(position);

        if (p != null) {
            TextView name = (TextView) v.findViewById(R.id.search_row_first);
            TextView symbol = (TextView) v.findViewById(R.id.search_row_second);

            String[] splittedArray = p.split("\\*");
            if (name != null) {
                name.setText(splittedArray[0]);
            }

            if (symbol != null) {
                if (splittedArray.length > 1){
                    symbol.setText(splittedArray[1]);
                    symbol.setVisibility(View.VISIBLE);
                }
            }
        }

        return v;
    }
}
