package com.ktmstudio.sanam.movie_box.SubViewHolder;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.ktmstudio.sanam.movie_box.Adapters.Horizontal_recyclerview;
import com.ktmstudio.sanam.movie_box.Adapters.Movie_info_RecyclerAdapter;
import com.ktmstudio.sanam.movie_box.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nitvdeveloper on 2/28/17.
 */

public class Third_viewHolder extends Movie_info_RecyclerAdapter.Super_ViewHolder {
    @BindView(R.id.horizontal_recycleview)
    public RecyclerView recyclerView;

    public Horizontal_recyclerview horizontal_recyclerview;

    public Third_viewHolder(View itemView) {
        super(itemView);



        ButterKnife.bind(this,itemView);

        recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL,false));

        horizontal_recyclerview = new Horizontal_recyclerview();
        recyclerView.setAdapter(horizontal_recyclerview);


    }
}
