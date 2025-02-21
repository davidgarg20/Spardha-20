package com.iitbhu.spardha2019.fragments.MainActivity;

/**
 * Created by abhinav on 5/14/2017.
 */

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iitbhu.spardha2019.R;
import com.iitbhu.spardha2019.activity.Game;
import com.iitbhu.spardha2019.fragments.Strings.strings;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    strings item1=new strings();
    private String[] titles = item1.titles;
    private int[] images = item1.icons;
    private String[] colors=item1.colors;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
//        viewHolder.itemTitle.setText(titles[i]);
        viewHolder.itemImage.setImageResource(images[i]);
//        viewHolder.itemTitle.setTextColor(Color.parseColor(colors[i]));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            viewHolder.itemImage.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(colors[i])));
        }
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public int currentItem;
        public ImageView itemImage;
        public TextView itemTitle;


        public ViewHolder(final View itemView) {
            super(itemView);
            itemImage = (ImageView)itemView.findViewById(R.id.imageofs);
//            itemTitle = (TextView)itemView.findViewById(R.id.textofs);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int position = getAdapterPosition();
                    Intent intent = new Intent(itemView.getContext(), Game.class);
                    // create the transition animation - the imagecontainer in the layouts
                    // of both activities are defined with android:transitionName="robot"
                    ActivityOptions options = ActivityOptions
                            .makeSceneTransitionAnimation((Activity) itemView.getContext(), itemView, "game");
                    // start the new activity
                    System.out.println("posotion before "+position);
//                    Toast.makeText(itemView.getContext(), "made upto here "+position , Toast.LENGTH_LONG).show();

                    Bundle b=options.toBundle();
                    b.putInt("position",position);
                    intent.putExtras(b);
                    itemView.getContext().startActivity(intent,b);

                    //PREDEIFINED
//                    Snackbar.make(v, "Click detected on item " + position,
//                            Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();

                }
            });
        }
    }
}