package com.aprigoldcorporation.allculator.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.aprigoldcorporation.allculator.R;
import com.aprigoldcorporation.allculator.ui.activities.Exam_DrivingLicenceActivity;
import com.aprigoldcorporation.allculator.ui.activities.Exam_OBPActivity;
import com.aprigoldcorporation.allculator.ui.activities.Exam_YDSActivity;
import com.aprigoldcorporation.allculator.ui.activities.Exam_YgsLysActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExamFragment extends Fragment implements View.OnClickListener
{


    public ExamFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_exam, container, false);


        LinearLayout LL_Ex_YGS = (LinearLayout) v.findViewById(R.id.LL_Ex_YGS);
        LL_Ex_YGS.setOnClickListener(this);
        LinearLayout LL_Ex_OBP = (LinearLayout) v.findViewById(R.id.LL__EX_OBP);
        LL_Ex_OBP.setOnClickListener(this);
        LinearLayout LL_Ex_YDS = (LinearLayout) v.findViewById(R.id.LL__EX_YDS);
        LL_Ex_YDS.setOnClickListener(this);
        LinearLayout LL_Ex_DrivingLicence = (LinearLayout) v.findViewById(R.id.LL__EX_DR);
        LL_Ex_DrivingLicence.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.LL_Ex_YGS:
                startActivity(new Intent(getActivity(),Exam_YgsLysActivity.class));
                break;
            case R.id.LL__EX_OBP:
                startActivity(new Intent(getActivity(),Exam_OBPActivity.class));
                break;
            case R.id.LL__EX_YDS:
                startActivity(new Intent(getActivity(),Exam_YDSActivity.class));
                break;
            case R.id.LL__EX_DR:
                startActivity(new Intent(getActivity(),Exam_DrivingLicenceActivity.class));
                break;
        }

    }
}
