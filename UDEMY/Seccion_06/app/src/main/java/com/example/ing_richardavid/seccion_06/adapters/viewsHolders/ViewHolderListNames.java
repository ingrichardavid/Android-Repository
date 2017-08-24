package com.example.ing_richardavid.seccion_06.adapters.viewsHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ing_richardavid.seccion_06.R;
import com.example.ing_richardavid.seccion_06.adapters.interfaces.OnItemClickListener;

/**
 * Created by ing_richardavid on 22-08-17.
 */

public class ViewHolderListNames extends RecyclerView.ViewHolder implements View.OnClickListener {

    /**
     * Objects, variables and constants.
     */

    private TextView txtVName;
    private OnItemClickListener onItemClickListener;

    /**
     * Builder.
     * @param itemView
     */
    public ViewHolderListNames(View itemView) {
        super(itemView);

        this.txtVName = (TextView)itemView.findViewById(R.id.layoutListIdName);
    }

    /**
     * Functions: OnClickListener.
     */

    @Override
    public void onClick(View view) {
        if (this.itemView.equals(view)) {
            this.onItemClickListener.onItemClick(this.txtVName.getText().toString(), getAdapterPosition());
        }
    }

    /**
     * Functions: Self.
     */

    public void bind(String name, OnItemClickListener onItemClickListener) {
        this.txtVName.setText(name);

        this.onItemClickListener = onItemClickListener;

        this.itemView.setOnClickListener(this);
    }

}
