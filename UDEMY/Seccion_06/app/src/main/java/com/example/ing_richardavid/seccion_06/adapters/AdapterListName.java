package com.example.ing_richardavid.seccion_06.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ing_richardavid.seccion_06.adapters.interfaces.OnItemClickListener;
import com.example.ing_richardavid.seccion_06.adapters.viewsHolders.ViewHolderListNames;

import java.util.List;

/**
 * Created by ing_richardavid on 22-08-17.
 */

public class AdapterListName extends RecyclerView.Adapter<ViewHolderListNames> {

    /**
     * Objects, variables and constants.
     */

    private List<String> listNames;
    private int layout;
    private OnItemClickListener onItemClickListener;

    /**
     * Builder.
     */

    public AdapterListName(List<String> listNames, int layout, OnItemClickListener onItemClickListener) {
        this.listNames = listNames;
        this.layout = layout;
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * Functions: RecyclerView.Adapter.
     */

    @Override
    public ViewHolderListNames onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(this.layout, parent, false);

        ViewHolderListNames viewHolderListNames = new ViewHolderListNames(view);

        return viewHolderListNames;
    }

    @Override
    public void onBindViewHolder(ViewHolderListNames holder, int position) {
        holder.bind(this.listNames.get(position), this.onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return this.listNames.size();
    }
}
