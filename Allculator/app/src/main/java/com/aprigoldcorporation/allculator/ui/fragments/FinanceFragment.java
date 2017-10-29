package com.aprigoldcorporation.allculator.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.aprigoldcorporation.allculator.R;
import com.aprigoldcorporation.allculator.ui.activities.Finance_CurrencyActivity;
import com.aprigoldcorporation.allculator.ui.activities.Finance_VehicleActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class FinanceFragment extends Fragment implements View.OnClickListener {


    public FinanceFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_finance, container, false);

        LinearLayout ll_currency = (LinearLayout) v.findViewById(R.id.LL_F_Currency);
        ll_currency.setOnClickListener(this);
        LinearLayout ll_vehicle = (LinearLayout) v.findViewById(R.id.LL_F_Vehicle);
        ll_vehicle.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.LL_F_Currency:
                startActivity(new Intent(getActivity(), Finance_CurrencyActivity.class));
                break;
            case R.id.LL_F_Vehicle:
                startActivity(new Intent(getActivity(), Finance_VehicleActivity.class));
                break;
        }
    }
}
