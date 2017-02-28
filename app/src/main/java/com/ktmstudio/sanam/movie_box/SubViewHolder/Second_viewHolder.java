package com.ktmstudio.sanam.movie_box.SubViewHolder;

import android.view.View;
import android.widget.TextView;

import com.ktmstudio.sanam.movie_box.Adapters.Movie_info_RecyclerAdapter;
import com.ktmstudio.sanam.movie_box.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by backtrack on 2/24/2017.
 */

public class Second_viewHolder extends Movie_info_RecyclerAdapter.Super_ViewHolder {
    @BindView(R.id.md_synopsis)
   public TextView md_synopsis;

    public Second_viewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
