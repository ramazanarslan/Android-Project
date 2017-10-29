package com.aprigoldcorporation.allculator.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.aprigoldcorporation.allculator.R;
import com.aprigoldcorporation.allculator.ui.activities.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment implements View.OnClickListener
{

    //// TODO: 14.08.2017 Tanımlamalar
    LinearLayout linearLayoutUnitConverter, linearLayoutFinance, linearLayoutExam, linearLayoutHealth, linearLayoutMath, linearLayoutInsurance, linearLayoutReligional, linearLayoutOthers;
    //// TODO: 14.08.2017 Tanımlamalar
    //        ((MainActivity) getActivity()).TabDegistir(0, editTextSavedGet.getText().toString(),gelendk,item1,item2,item3,item4);

    public CategoryFragment()
    {
        // Required empty public constructor
    }

    public void All_FindViewByIds(View view)
    {

        linearLayoutUnitConverter = (LinearLayout) view.findViewById(R.id.LL_UnitConverter);
        linearLayoutFinance = (LinearLayout) view.findViewById(R.id.LL_Finans);
        linearLayoutExam = (LinearLayout) view.findViewById(R.id.LL_Exam);
        linearLayoutHealth = (LinearLayout) view.findViewById(R.id.LL_Health);
        linearLayoutMath = (LinearLayout) view.findViewById(R.id.LL_Math);
        linearLayoutInsurance = (LinearLayout) view.findViewById(R.id.LL_Insurance);

        linearLayoutReligional = (LinearLayout) view.findViewById(R.id.LL_Religional);
        linearLayoutOthers = (LinearLayout) view.findViewById(R.id.LL_Others);

    }

    public void SetOnClickListeners()
    {

        linearLayoutUnitConverter.setOnClickListener(this);
        linearLayoutFinance.setOnClickListener(this);
        linearLayoutExam.setOnClickListener(this);
        linearLayoutHealth.setOnClickListener(this);
        linearLayoutMath.setOnClickListener(this);
        linearLayoutInsurance.setOnClickListener(this);
        linearLayoutReligional.setOnClickListener(this);
        linearLayoutOthers.setOnClickListener(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        All_FindViewByIds(view);

        SetOnClickListeners();


        return view;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.LL_UnitConverter:
                ((MainActivity) getActivity()).TabDegistir(2);
                break;
            case R.id.LL_Finans:

                ((MainActivity) getActivity()).TabDegistir(3);
                break;
            case R.id.LL_Exam:

                ((MainActivity) getActivity()).TabDegistir(4);
                break;
            case R.id.LL_Health:

                ((MainActivity) getActivity()).TabDegistir(5);
                break;
            case R.id.LL_Math:

                ((MainActivity) getActivity()).TabDegistir(6);
                break;
            case R.id.LL_Insurance:

                ((MainActivity) getActivity()).TabDegistir(7);
                break;
            case R.id.LL_Religional:

                ((MainActivity) getActivity()).TabDegistir(8);
                break;
            case R.id.LL_Others:

                ((MainActivity) getActivity()).TabDegistir(9);
                break;

        }

    }
}
