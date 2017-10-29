package com.aprigoldcorporation.allculator.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.aprigoldcorporation.allculator.R;
import com.aprigoldcorporation.allculator.ui.activities.Math_PercentActivity;
import com.aprigoldcorporation.allculator.ui.activities.Math_Profit_LossActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MathFragment extends Fragment implements View.OnClickListener {


    public MathFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_math, container, false);

        LinearLayout LL_Math_Percent = (LinearLayout) v.findViewById(R.id.LL_Math_Percent);
        LinearLayout LL_Math_ProfitorLoss = (LinearLayout) v.findViewById(R.id.LL_Math_ProfitorLoss);
        LL_Math_Percent.setOnClickListener(this);
        LL_Math_ProfitorLoss.setOnClickListener(this);


        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.LL_Math_Percent:
                startActivity(new Intent(getActivity(), Math_PercentActivity.class));
                break;
            case R.id.LL_Math_ProfitorLoss:
                startActivity(new Intent(getActivity(), Math_Profit_LossActivity.class));
                break;
        }
    }
}
