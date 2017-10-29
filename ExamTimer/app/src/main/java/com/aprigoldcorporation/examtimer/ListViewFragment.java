package com.aprigoldcorporation.examtimer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.aprigoldcorporation.examtimer.R;
import com.aprigoldcorporation.examtimer.adapter.ListViewAdapter;


import java.util.ArrayList;

/**
 * Created by sonu on 08/02/17.
 */
public class ListViewFragment extends Fragment
{
    private Context context;
    private ListViewAdapter adapter;
    private ArrayList<String> arrayList;
    String llabel;
    int lgelendk;
    int litem1, litem2, litem3, litem4;
    private FloatingActionButton fabTab1;

    
    public ListViewFragment()
    {
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.list_view_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        loadListView(view);

               fabTab1=(FloatingActionButton)view.findViewById(R.id.fabTab1);
        fabTab1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(context, adibilgisi, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadListView(View view)
    {
        ListView listView = (ListView) view.findViewById(R.id.list_view);
        arrayList = new ArrayList<>();
        adapter = new ListViewAdapter(context, arrayList);
        listView.setAdapter(adapter);
    }


    public void SavedOlustur(String label, int gelendk, int item1, int item2, int item3, int item4)
    {

        llabel = label;
        lgelendk = gelendk;
        litem1 = item1;
        litem2 = item2;
        litem3 = item3;
        litem4 = item4;
    }
}
