package com.ktmstudio.sanam.movie_box.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ktmstudio.sanam.movie_box.Infrastucture.Movies_Application;
import com.ktmstudio.sanam.movie_box.Model.Sub_Movie_detail;
import com.ktmstudio.sanam.movie_box.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by backtrack on 2/21/2017.
 */

public class Movies_Played_Adapter extends RecyclerView.Adapter<Movies_Played_Adapter.MyHolder> {


    private List<Sub_Movie_detail> movie_details;

    public Movies_Played_Adapter(List<Sub_Movie_detail> movie_details) {
        this.movie_details = movie_details;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row, parent, false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        Sub_Movie_detail sub_movie_detail = movie_details.get(position);

        holder.title.setText(sub_movie_detail.getDoriginal_title());
        holder.rating.setText(sub_movie_detail.getDvote_average());

        Picasso.with(holder.itemView.getContext())
                .load(Movies_Application.picture_url + sub_movie_detail.getdPosterpath())
                .fit()
                .centerCrop()
                .into(holder.poster);


    }

    @Override
    public int getItemCount() {
        return movie_details.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.recycler_text)
        TextView title;
        @BindView(R.id.rating)
        TextView rating;


        @BindView(R.id.recycler_image)
        ImageView poster;

        public MyHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);


        }
    }
}
