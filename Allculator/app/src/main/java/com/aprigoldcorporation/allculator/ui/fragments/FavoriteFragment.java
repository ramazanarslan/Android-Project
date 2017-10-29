package com.aprigoldcorporation.allculator.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aprigoldcorporation.allculator.R;
import com.aprigoldcorporation.allculator.ui.adapter.FavoriteAdapter;
import com.aprigoldcorporation.allculator.ui.adapter.SpacesItemDecoration;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment
{

    RecyclerView recyclerView;
    GridLayoutManager layoutManager;

    private String nameList[] = {"raama", "asdmas", "msda", "dada", "123", "2123", "12312312", "1231232323233333 12", "hesap makinesi"};
    private int iconId[] = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

    public FavoriteFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.RV_favorites);
        layoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        FavoriteAdapter favoriteAdapter = new FavoriteAdapter(getContext(), iconId, nameList);
        recyclerView.setAdapter(favoriteAdapter);

        //// STOPSHIP: 19.08.2017  recyler view spacing items
        recyclerView.addItemDecoration(new SpacesItemDecoration(getResources().getDimensionPixelSize(R.dimen.spacing)));
        return view;
    }

}
