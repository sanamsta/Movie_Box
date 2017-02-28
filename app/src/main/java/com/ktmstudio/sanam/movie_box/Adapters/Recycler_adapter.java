package com.ktmstudio.sanam.movie_box.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ktmstudio.sanam.movie_box.Infrastucture.Movies_Application;
import com.ktmstudio.sanam.movie_box.Model.Movie_model;
import com.ktmstudio.sanam.movie_box.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by iosdeveloper on 10/26/16.
 */

public class Recycler_adapter extends RecyclerView.Adapter<Recycler_adapter.Holder> {

    private ArrayList<Movie_model> arrayList;
    private Clicklistener_recycler clicklistenerRecycler;
    int Drawer_index;
    String pic;

    public Recycler_adapter(ArrayList<Movie_model> mylist,int Drawer_index ) {

        arrayList = mylist;
        this.Drawer_index = Drawer_index;



    }

    public void delete(int position) {

        arrayList.remove(position);
        notifyItemRemoved(position);


    }

    public void setRecyclerClicklistener(Clicklistener_recycler recyclerClicklistener) {
        this.clicklistenerRecycler = recyclerClicklistener;

    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row, parent, false);


        return new Holder(v);
    }


    @Override
    public void onBindViewHolder(Holder holder, int position) {

        Movie_model movieData = arrayList.get(position);



        switch (Drawer_index){
            case 0:
                holder.textView.setText(movieData.getMovie_title()+" ("+movieData.getMovie_releasedate()+")");
                pic = movieData.getMovie_poster();

                break;
            case 1:
                holder.textView.setText(movieData.getSeriesName()+" ("+movieData.getReleaseDate()+")");
                pic = movieData.getMovie_poster();
                break;

            case 2:holder.textView.setText(movieData.getMovie_title()+" ("+movieData.getMovie_releasedate()+")");
                pic = movieData.getMovie_poster();
                break;
            case 3:
                holder.textView.setText(movieData.getPersonName());
                holder.rating.setVisibility(View.INVISIBLE);
                pic = movieData.getProfilePath();
                break;
            default: holder.textView.setText("No data");
        }

        holder.rating.setText(movieData.getRating());

        Picasso.with(holder.itemView.getContext()).
                load(Movies_Application.picture_url+pic)
                .fit()
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void filtered_movieList(ArrayList<Movie_model> movie_datas) {

        this.arrayList = movie_datas;
        notifyDataSetChanged();
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.recycler_text)
        TextView textView;

        @BindView(R.id.recycler_image)
        ImageView imageView;

        @BindView(R.id.rating)
        TextView rating;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {



          //  delete(getAdapterPosition());

            if (clicklistenerRecycler != null) {

                clicklistenerRecycler.recyclerview_clicked(view, getAdapterPosition(),arrayList.get(getAdapterPosition()));
            }

        }


    }

    public interface Clicklistener_recycler
    {
        public void recyclerview_clicked(View v, int position,Movie_model movieData);

    }
}
