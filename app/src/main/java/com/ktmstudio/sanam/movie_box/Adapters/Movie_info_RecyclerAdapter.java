package com.ktmstudio.sanam.movie_box.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ktmstudio.sanam.movie_box.Model.Movie_details;
import com.ktmstudio.sanam.movie_box.Model.Movie_model;
import com.ktmstudio.sanam.movie_box.R;
import com.ktmstudio.sanam.movie_box.SubViewHolder.First_viewHolder;
import com.ktmstudio.sanam.movie_box.SubViewHolder.Second_viewHolder;
import com.ktmstudio.sanam.movie_box.SubViewHolder.Third_viewHolder;

/**
 * Created by backtrack on 2/24/2017.
 */

public class Movie_info_RecyclerAdapter extends RecyclerView.Adapter<Movie_info_RecyclerAdapter.Super_ViewHolder> {

    Movie_model movie_model;
    Movie_details movie_details;
    String genre_name="Genre : ";

    public Movie_info_RecyclerAdapter(Movie_model movie_model,Movie_details movie_details) {
        this.movie_model =movie_model;
        this.movie_details =movie_details;
    }

    @Override
    public Super_ViewHolder onCreateViewHolder(ViewGroup parent, int position) {

        switch (position) {

            case 0:
                return new First_viewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_first_row, parent, false));

            case 1:

                return new Second_viewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_second_row, parent, false));

            case 2:
                return new Third_viewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_recycleviewlayout,parent,false));





        }


        return null;
    }

    @Override
    public void onBindViewHolder(Super_ViewHolder holder, int position) {


        switch (position){
            case 0:
                First_viewHolder mholder = (First_viewHolder) holder;
                mholder.md_title.setText(movie_model.getMovie_title());
                mholder.md_vote.setText(movie_model.getRating());
                mholder.md_votecount.setText(movie_model.getVote_count()+" vote counts");
                mholder.md_date.setText(movie_model.getMovie_releasedate());
                if (movie_details!=null){
                    mholder.md_status.setText("Status : "+movie_details.getMd_status());

                    mholder.md_genre.setText(get_genrename());

                    mholder.md_releasedate.setText("Release : "+movie_details.getMd_release_date());
                }
                break;

            case 1:
                Second_viewHolder mHolder = (Second_viewHolder) holder;

                mHolder.md_synopsis.setText(movie_model.getOverview());
                break;

            case 2:

                Third_viewHolder mHolder3 = (Third_viewHolder)holder;
                mHolder3.horizontal_recyclerview.setData();
                break;

        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public static class Super_ViewHolder extends RecyclerView.ViewHolder {


        public Super_ViewHolder(View itemView) {
            super(itemView);
        }
    }


    public String get_genrename(){


        if (movie_details!=null) {

            for (Movie_details.Genre genre : movie_details.getGenreList()
                    ) {

                genre_name += genre.getGenre_name()+", ";
            }

        }
        return genre_name;
    }
}
