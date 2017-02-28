package com.ktmstudio.sanam.movie_box.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ktmstudio.sanam.movie_box.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nitvdeveloper on 2/28/17.
 */

public class Horizontal_recyclerview extends RecyclerView.Adapter<Horizontal_recyclerview.ViewHolder> {




    public void setData() {
    }

    @Override
    public Horizontal_recyclerview.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_third_row,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Horizontal_recyclerview.ViewHolder holder, int position) {

        holder.castpic.setImageResource(R.drawable.sanam);


    }

    @Override
    public int getItemCount() {
        return 10;
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.third_mv_castpic)
        ImageView castpic;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
