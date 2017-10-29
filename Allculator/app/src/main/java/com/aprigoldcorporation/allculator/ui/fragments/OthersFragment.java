package com.aprigoldcorporation.allculator.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.aprigoldcorporation.allculator.R;
import com.aprigoldcorporation.allculator.ui.activities.Other_AdvancedCalculator;
import com.aprigoldcorporation.allculator.ui.activities.Other_SimpleCalculator;

/**
 * A simple {@link Fragment} subclass.
 */
public class OthersFragment extends Fragment implements View.OnClickListener
{


    public OthersFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v= inflater.inflate(R.layout.fragment_others, container, false);

        LinearLayout LL_Ot_basic = (LinearLayout) v.findViewById(R.id.LL_Ot_basic);
        LL_Ot_basic.setOnClickListener(this);
        LinearLayout LL_Ot_Sci = (LinearLayout) v.findViewById(R.id.LL_Ot_Sci);
        LL_Ot_Sci.setOnClickListener(this);
        LinearLayout LL_Ot_ruler = (LinearLayout) v.findViewById(R.id.LL_Ot_ruler);
        LL_Ot_ruler.setOnClickListener(this);
       return v;
    }

    @Override
    public void onClick(View v)
    {

        switch (v.getId()){
            case R.id.LL_Ot_basic:

                Intent i1=new Intent(getActivity(), Other_SimpleCalculator.class);
                startActivity(i1);

                break;
            case R.id.LL_Ot_Sci:
                Intent i2=new Intent(getActivity(), Other_AdvancedCalculator.class);
                startActivity(i2);
                break;
            case R.id.LL_Ot_ruler:
                Intent i3=new Intent(getActivity(), com.aprigoldcorporation.allculator.ui.activities.ruler.Main.RulerMainActivity.class);
                startActivity(i3);
                break;
        }
    }
}
