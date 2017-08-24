package com.example.ing_richardavid.seccion_06.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ing_richardavid.seccion_06.adapters.interfaces.OnItemClickListener;
import com.example.ing_richardavid.seccion_06.adapters.viewsHolders.ViewHolderListMovies;
import com.example.ing_richardavid.seccion_06.models.entities.Movie;

import java.util.List;

/**
 * Created by ing_richardavid on 23-08-17.
 */

public class AdapterListMovies extends RecyclerView.Adapter<ViewHolderListMovies> {

    /**
     * Objects, variables and constants.
     */

    private List<Movie> listMovies;
    private int layout;
    private OnItemClickListener onItemClickListener;

    /**
     * Builder.
     * @param listMovies
     * @param layout
     * @param onItemClickListener
     */

    public AdapterListMovies(List<Movie> listMovies, int layout, OnItemClickListener onItemClickListener) {
        this.listMovies = listMovies;
        this.layout = layout;
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * Functions: RecyclerView.Adapter.
     */

    @Override
    public ViewHolderListMovies onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(this.layout, parent, false);

        ViewHolderListMovies viewHolderListMovies = new ViewHolderListMovies(view);
        viewHolderListMovies.setContext(parent.getContext());

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
}
