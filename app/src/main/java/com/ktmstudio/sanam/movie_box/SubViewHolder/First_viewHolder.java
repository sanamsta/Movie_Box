package com.ktmstudio.sanam.movie_box.SubViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ktmstudio.sanam.movie_box.Adapters.Movie_info_RecyclerAdapter;
import com.ktmstudio.sanam.movie_box.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by backtrack on 2/24/2017.
 */

public class First_viewHolder extends Movie_info_RecyclerAdapter.Super_ViewHolder {

    @BindView(R.id.md_title)
    public TextView md_title;

    @BindView(R.id.md_vote)
   public TextView md_vote;

    @BindView(R.id.md_votecount)
    public TextView md_votecount;

    @BindView(R.id.md_date)
    public  TextView md_date;

    @BindView(R.id.md_genre)
    public TextView md_genre;

    @BindView(R.id.md_status)
    public TextView md_status;

    @BindView(R.id.md_releasedate)
    public TextView md_releasedate;

    public First_viewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this,itemView);
    }
}
