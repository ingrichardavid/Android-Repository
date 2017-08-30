package com.example.ing_richardavid.seccion_06_movies.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ing_richardavid.seccion_06_movies.adapters.interfaces.OnItemClickListener;
import com.example.ing_richardavid.seccion_06_movies.adapters.viewHolders.ViewHolderListMovies;
import com.example.ing_richardavid.seccion_06_movies.models.entities.Movie;

import java.util.List;

/**
 * Created by ing_richardavid on 24-08-17.
 */

public class AdapterListMovies extends RecyclerView.Adapter<ViewHolderListMovies> {

    /**
     * Objects, variables and constants.
     */

    private List<Movie> listMovies;
    private int layout;
    private OnItemClickListener onItemClickListener;
    private Activity activity;

    /**
     * Builder.
     * @param listMovies
     * @param layout
     * @param onItemClickListener
     */

    public AdapterListMovies(List<Movie> listMovies, Activity activity, int layout, OnItemClickListener onItemClickListener) {
        this.listMovies = listMovies;
        this.layout = layout;
        this.onItemClickListener = onItemClickListener;
        this.activity = activity;
    }

    /**
     * Functions: RecyclerView.Adapter.
     */

    @Override
    public ViewHolderListMovies onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(this.layout, parent, false);

        ViewHolderListMovies viewHolderListMovies = new ViewHolderListMovies(view);
        viewHolderListMovies.initialSetup(parent.getContext(), this.activity, this);

        return viewHolderListMovies;
    }

    @Override
    public void onBindViewHolder(ViewHolderListMovies holder, int position) {
        holder.bind(this.listMovies.get(position), this.onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return this.listMovies.size();
    }

    /**
     * Functions: Self.
     */

    public void deleteMovie(int position) {
        this.listMovies.remove(position);
    }

    public void updateMovieQuantity(int position, int quantity) {
        this.listMovies.get(position).setQuantity(quantity);
    }
}
