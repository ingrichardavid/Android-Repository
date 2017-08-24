package com.example.ing_richardavid.seccion_06_movies.adapters.viewHolders;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ing_richardavid.seccion_06_movies.R;
import com.example.ing_richardavid.seccion_06_movies.adapters.AdapterListMovies;
import com.example.ing_richardavid.seccion_06_movies.adapters.interfaces.OnItemClickListener;
import com.example.ing_richardavid.seccion_06_movies.models.entities.Movie;
import com.squareup.picasso.Picasso;

/**
 * Created by ing_richardavid on 24-08-17.
 */

public class ViewHolderListMovies extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

    /**
     * View components.
     */

    private ImageView imgVPoster;
    private TextView txtVName;
    private TextView txtVDescription;
    private TextView txtVQuantity;
    private Context context;
    private OnItemClickListener onItemClickListener;
    private Activity activity;
    private CardView cardView;
    private AdapterListMovies adapterListMovies;

    /**
     * Functions: RecyclerView.ViewHolder.
     */

    public ViewHolderListMovies(View itemView) {
        super(itemView);

        this.imgVPoster = (ImageView)itemView.findViewById(R.id.layoutMoviesIdImgVPoster);

        this.txtVName = (TextView)itemView.findViewById(R.id.layoutMoviesIdTxtVName);

        this.txtVDescription = (TextView)itemView.findViewById(R.id.layoutMoviesIdTxtVDescription);

        this.txtVQuantity = (TextView)itemView.findViewById(R.id.layoutMoviesIdTxtVCounter);

        this.cardView = (CardView)itemView.findViewById(R.id.layoutMoviesIdCVContainer);

        this.cardView.setOnCreateContextMenuListener(this);
    }

    /**
     * Functions: OnItemClickListener.
     */

    @Override
    public void onClick(View view) {
        this.onItemClickListener.onItemClick(
                new Movie(this.txtVName.getText().toString(),
                        this.txtVDescription.getText().toString(),
                        Integer.valueOf(this.txtVQuantity.getText().toString()),
                        this.imgVPoster.getId()),
                this.getAdapterPosition());
    }


    /**
     * Functions: OnCreateContextMenuListener.
     */

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        this.activity.getMenuInflater().inflate(R.menu.menu_context, contextMenu);

        contextMenu.setHeaderTitle(this.txtVName.getText().toString());
        contextMenu.setHeaderIcon(this.imgVPoster.getDrawable());

        for (int i = 0; i < contextMenu.size(); i++) {
            contextMenu.getItem(i).setOnMenuItemClickListener(this);
        }
    }

    /**
     * Functions: OnMenuItemClickListener.
     */

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menuContextIdDelete:
                this.adapterListMovies.deleteMovie(this.getAdapterPosition());

                this.adapterListMovies.notifyItemRemoved(this.getAdapterPosition());

                return true;
            case R.id.menuContextIdReset:
                if (Integer.valueOf(this.txtVQuantity.getText().toString()) > Movie.INITIAL_QUANTITY) {
                    this.txtVQuantity.setText(String.valueOf(Movie.INITIAL_QUANTITY));
                    this.txtVQuantity.setTextColor(Color.BLACK);

                    this.adapterListMovies.updateMovieQuantity(this.getAdapterPosition(), Movie.INITIAL_QUANTITY);
                } else {
                    Toast.makeText(this.activity, "La catidad ya es 0.", Toast.LENGTH_SHORT).show();
                }

                return true;
            default:
                return false;
        }
    }

    /**
     * Functions: Self.
     */

    public void bind(Movie movie, OnItemClickListener onItemClickListener) {
        Picasso.with(this.context).load(movie.getPoster()).fit().into(this.imgVPoster);

        this.txtVName.setText(movie.getName());

        this.txtVDescription.setText(movie.getDescription());

        this.txtVQuantity.setText(String.valueOf(movie.getQuantity()));

        if (movie.getQuantity() >= Movie.LIMIT_QUANTITY) {
            this.txtVQuantity.setTextColor(Color.RED);
        } else {
            this.txtVQuantity.setTextColor(Color.BLACK);
        }

        this.onItemClickListener = onItemClickListener;

        this.imgVPoster.setOnClickListener(this);
    }

    public void initialSetup(Context context, Activity activity, AdapterListMovies adapterListMovies) {
        this.context = context;
        this.activity = activity;
        this.adapterListMovies = adapterListMovies;
    }

}
