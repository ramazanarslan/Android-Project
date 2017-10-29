package com.aprigoldcorporation.allculator.ui.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aprigoldcorporation.allculator.R;

/**
 * Created by RamazanArslan on 15.08.2017.
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder>
{

    private Context context;
    private int Images[];
    private String names[];

    public FavoriteAdapter(Context context, int[] images, String[] names)
    {
        this.context = context;
        Images = images;
        this.names = names;
    }

    @Override
    public FavoriteHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_grid_layout,null);
        FavoriteHolder favoriteHolder= new FavoriteHolder(layout);
        return favoriteHolder;
    }

    @Override
    public void onBindViewHolder(FavoriteHolder holder, int position)
    {


        holder.img.setImageResource(Images[position]);
        holder.txt.setText(names[position]);

    }

    @Override
    public int getItemCount()
    {
        return names.length;
    }

    public static class FavoriteHolder extends RecyclerView.ViewHolder
    {
        ImageView img;
        TextView txt;

        public FavoriteHolder(View itemView)
        {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.imageViewFavGridLay);
            txt=(TextView) itemView.findViewById(R.id.textViewFavGridLay);
        }

    }

}
