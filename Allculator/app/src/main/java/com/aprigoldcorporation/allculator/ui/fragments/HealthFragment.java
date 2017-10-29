package com.aprigoldcorporation.allculator.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.aprigoldcorporation.allculator.R;
import com.aprigoldcorporation.allculator.ui.activities.Health_BodyMassIndexActivity;
import com.aprigoldcorporation.allculator.ui.activities.Health_IdealWeightActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class HealthFragment extends Fragment implements View.OnClickListener {


    public HealthFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_health, container, false);

        LinearLayout ideal_weight = (LinearLayout) v.findViewById(R.id.LL_Health_IdealWeight);
        ideal_weight.setOnClickListener(this);
        LinearLayout body_mass_index = (LinearLayout) v.findViewById(R.id.LL_Health_BodyMassIndex);
        body_mass_index.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.LL_Health_IdealWeight:
                startActivity(new Intent(getActivity(), Health_IdealWeightActivity.class));
                break;
            case R.id.LL_Health_BodyMassIndex:
                startActivity(new Intent(getActivity(), Health_BodyMassIndexActivity.class));
                break;
        }
    }
}
