package com.aprigoldcorporation.allculator.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.aprigoldcorporation.allculator.R;
import com.aprigoldcorporation.allculator.ui.activities.UnitConverterActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class UnitConverterFragment extends Fragment implements View.OnClickListener {


    public UnitConverterFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_unit_converter, container, false);

        LinearLayout UC_Weight = (LinearLayout) v.findViewById(R.id.LL_UC_Weight);
        UC_Weight.setOnClickListener(this);
        LinearLayout UC_Length = (LinearLayout) v.findViewById(R.id.LL_UC_Lenght);
        UC_Length.setOnClickListener(this);
        LinearLayout UC_Volume = (LinearLayout) v.findViewById(R.id.LL_UC_Volume);
        UC_Volume.setOnClickListener(this);
        LinearLayout UC_Temperature = (LinearLayout) v.findViewById(R.id.LL_UC_Temp);
        UC_Temperature.setOnClickListener(this);
        LinearLayout UC_Power = (LinearLayout) v.findViewById(R.id.LL_UC_Power);
        UC_Power.setOnClickListener(this);
        LinearLayout UC_Area = (LinearLayout) v.findViewById(R.id.LL_UC_Area);
        UC_Area.setOnClickListener(this);
        LinearLayout UC_Pressure = (LinearLayout) v.findViewById(R.id.LL_Press);
        UC_Pressure.setOnClickListener(this);
        LinearLayout UC_Time = (LinearLayout) v.findViewById(R.id.LL_UC_Time);
        UC_Time.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.LL_UC_Weight:
                UnitConverterActivity.getInstance(getActivity(),R.array.weight_units,getString(R.string.Weight),0);
                break;
            case R.id.LL_UC_Lenght:
                UnitConverterActivity.getInstance(getActivity(),R.array.length_units,getString(R.string.Length),1);
                break;
            case R.id.LL_UC_Volume:
                UnitConverterActivity.getInstance(getActivity(),R.array.volume_units,getString(R.string.Volume),2);
                break;
            case R.id.LL_UC_Temp:
                UnitConverterActivity.getInstance(getActivity(),R.array.temperature_units,getString(R.string.Temperature),3);
                break;
            case R.id.LL_UC_Power:
                UnitConverterActivity.getInstance(getActivity(),R.array.power_units,getString(R.string.Power),4);
                break;
            case R.id.LL_UC_Area:
                UnitConverterActivity.getInstance(getActivity(),R.array.area_units,getString(R.string.Area),5);
                break;
            case R.id.LL_Press:
                UnitConverterActivity.getInstance(getActivity(),R.array.pressure_units,getString(R.string.Pressure),6);
                break;
            case R.id.LL_UC_Time:
                UnitConverterActivity.getInstance(getActivity(),R.array.time_units,getString(R.string.Time),7);
                break;
        }
    }
}
