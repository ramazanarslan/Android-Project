package com.aprigoldcorporation.examtimer.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aprigoldcorporation.examtimer.R;

import java.util.ArrayList;

/**
 * Created by sonu on 08/02/17.
 */

public class ListViewAdapter extends BaseAdapter
{
    public String takenNumber;

    private Context context;
    private ArrayList<String> arrayList;
    private LayoutInflater inflater;


    public ListViewAdapter(Context context, ArrayList<String> arrayList)
    {
        this.context = context;
        this.arrayList = arrayList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount()
    {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i)
    {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(final int i, View view, final ViewGroup viewGroup)
    {


        final ViewHolder viewHolder;
        if (view == null)
        {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.list_custom_row_layout, viewGroup, false);
            viewHolder.label = (TextView) view.findViewById(R.id.label);
            viewHolder.btnEdit = (ImageButton) view.findViewById(R.id.btnEdit);
            viewHolder.btnPlay = (ImageButton) view.findViewById(R.id.btnPlay);

            view.setTag(viewHolder);
        }
        else
            viewHolder = (ViewHolder) view.getTag();


        viewHolder.label.setText(arrayList.get(i));


        viewHolder.btnPlay.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(context, "play", Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.btnEdit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
                View mView = inflater.inflate(R.layout.dialog_set_number, null);
                final NumberPicker mNumberPicker;
                mNumberPicker = (NumberPicker) mView.findViewById(R.id.numberPicker);

                mNumberPicker.setMinValue(0);
                mNumberPicker.setMaxValue(60);

                mNumberPicker.setValue(20);
                mNumberPicker.setWrapSelectorWheel(false);
                mBuilder.setTitle(context.getString(R.string.ChangeTheTime));

                mBuilder.setPositiveButton(context.getString(R.string.Cancel), new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();

                    }
                });
                mBuilder.setNeutralButton("Delete", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        viewHolder.label.setText("");
                        dialog.dismiss();

                    }
                });
                mBuilder.setNegativeButton(context.getString(R.string.Apply), new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                            viewHolder.label.setText(context.getString(R.string.Remember) + "\n" + " " + context.getString(R.string.Last) + " " + takenNumber
                                    + " " + context.getString(R.string.Min));



                    }
                });
                mBuilder.setView(mView);
                AlertDialog alertDialog = mBuilder.create();
                alertDialog.show();
            }
        });


        return view;
    }

    private class ViewHolder
    {
        private TextView label;
        private ImageButton btnPlay;
        private ImageButton btnEdit;

    }

}