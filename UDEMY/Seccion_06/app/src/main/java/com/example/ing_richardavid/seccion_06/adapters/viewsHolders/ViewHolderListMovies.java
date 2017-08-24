package com.example.ing_richardavid.seccion_06.adapters.viewsHolders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ing_richardavid.seccion_06.R;
import com.example.ing_richardavid.seccion_06.adapters.interfaces.OnItemClickListener;
import com.example.ing_richardavid.seccion_06.models.entities.Movie;
import com.squareup.picasso.Picasso;

/**
 * Created by ing_richardavid on 23-08-17.
 */

public class ViewHolderListMovies extends RecyclerView.ViewHolder implements View.OnClickListener {

    /**
     * Objects, variables and constants.
     */

    private ImageView imgPoster;
    private TextView txtVName;
    private OnItemClickListener onItemClickListener;
    private Context context;

    /**
     * Builder.
     * @param itemView
     */
    public ViewHolderListMovies(View itemView) {
        super(itemView);

        this.imgPoster = (ImageView)itemView.findViewById(R.id.layoutListMoviesCVIdImgVPoster);

        this.txtVName = (TextView)itemView.findViewById(R.id.layoutListMoviesCVIdTxtVName);
    }

    /**
     * Functions: OnClickListener.
     */

    @Override
    public void onClick(View view) {
        this.onItemClickListener.onItemClick(new Movie(this.txtVName.getText().toString(), this.imgPoster.getId()), getAdapterPosition());
    }

    /**
     * Functions: Self.
     */

    public void bind(Movie movie, OnItemClickListener onItemClickListener) {
        Picasso.with(this.context).load(movie.getPoster()).fit().into(this.imgPoster);


        this.txtVName.setText(movie.getName());

        this.onItemClickListener = onItemClickListener;

        this.itemView.setOnClickListener(this);

        this.imgPoster.setOnClickListener(this);

        this.txtVName.setOnClickListener(this);
    }

    /**
     * Getters and setters.
     */

    public void setContext(Context context) {
        this.context = context;
    }
}
