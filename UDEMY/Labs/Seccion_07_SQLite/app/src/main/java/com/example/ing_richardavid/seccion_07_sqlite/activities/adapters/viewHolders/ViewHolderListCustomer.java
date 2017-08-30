package com.example.ing_richardavid.seccion_07_sqlite.activities.adapters.viewHolders;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.ing_richardavid.seccion_07_sqlite.R;
import com.example.ing_richardavid.seccion_07_sqlite.activities.adapters.AdapterListCustomers;
import com.example.ing_richardavid.seccion_07_sqlite.activities.adapters.interfaces.OnItemClickListener;
import com.example.ing_richardavid.seccion_07_sqlite.models.entities.Customer;

/**
 * Created by ing_richardavid on 30-08-17.
 */

public class ViewHolderListCustomer extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

    /**
     * View components.
     */

    private TextView txtVName;
    private TextView txtVDate;
    private OnItemClickListener onItemClickListener;
    private Customer customer;
    private Activity activity;
    private AdapterListCustomers adapterListCustomers;

    /**
     * Builder: RecyclerView.ViewHolder.
     */

    public ViewHolderListCustomer(View itemView, Activity activity, AdapterListCustomers adapterListCustomers){
        super(itemView);

        this.txtVName = (TextView)itemView.findViewById(R.id.layoutCustomerListIdTxtVName);

        this.txtVDate = (TextView)itemView.findViewById(R.id.layoutCustomerListIdTxtVDate);

        this.activity = activity;

        this.adapterListCustomers = adapterListCustomers;

        itemView.setOnCreateContextMenuListener(this);
    }

    /**
     * Functions: OnClickListener.
     */

    @Override
    public void onClick(View view) {
        this.onItemClickListener.onItemClick(
                new Customer(
                        this.customer.getDni(),
                        this.customer.getName(),
                        this.customer.getDate_creation()),
                this.getAdapterPosition());
    }

    /**
     * Functions: View.OnCreateContextMenuListener.
     */

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        this.activity.getMenuInflater().inflate(R.menu.menu_context, contextMenu);

        contextMenu.setHeaderTitle(this.txtVName.getText().toString() + " - " + this.txtVDate.getText().toString());

        for (int i = 0; i < contextMenu.size(); i++) {
            contextMenu.getItem(i).setOnMenuItemClickListener(this);
        }
    }

    /**
     * Functions: MenuItem.OnMenuItemClickListener.
     */

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menuContextIdDelete:

                this.adapterListCustomers.deleteMovie(this.getAdapterPosition());

                this.adapterListCustomers.notifyItemRemoved(this.getAdapterPosition());

                return true;
            default:
                return false;
        }
    }

    /**
     * Functions: Self.
     */

    public void bind(Customer customer, OnItemClickListener onItemClickListener) {
        this.customer = customer;

        this.txtVName.setText(customer.getName());

        this.txtVDate.setText(customer.getDate_creation());

        this.onItemClickListener = onItemClickListener;

        this.itemView.setOnClickListener(this);
    }
}
