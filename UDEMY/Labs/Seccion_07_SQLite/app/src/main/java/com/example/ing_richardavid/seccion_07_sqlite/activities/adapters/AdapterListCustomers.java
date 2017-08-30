package com.example.ing_richardavid.seccion_07_sqlite.activities.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ing_richardavid.seccion_07_sqlite.activities.adapters.interfaces.OnItemClickListener;
import com.example.ing_richardavid.seccion_07_sqlite.activities.adapters.viewHolders.ViewHolderListCustomer;
import com.example.ing_richardavid.seccion_07_sqlite.models.entities.Customer;
import com.example.ing_richardavid.seccion_07_sqlite.models.implementions.CustomerImplementationDAO;

import java.util.List;

/**
 * Created by ing_richardavid on 30-08-17.
 */

public class AdapterListCustomers extends RecyclerView.Adapter<ViewHolderListCustomer> {

    /**
     * Objects, variables and constants.
     */

    private List<Customer> listCustomer;
    private int layout;
    private OnItemClickListener onItemClickListener;
    private CustomerImplementationDAO customerImplementationDAO;
    private Activity activity;

    /**
     * Builder.
     * @param listCustomer
     * @param layout
     * @param onItemClickListener
     */

    public AdapterListCustomers(Activity activity, List<Customer> listCustomer, int layout, OnItemClickListener onItemClickListener, CustomerImplementationDAO customerImplementationDAO) {
        this.activity = activity;
        this.listCustomer = listCustomer;
        this.layout = layout;
        this.onItemClickListener = onItemClickListener;
        this.customerImplementationDAO = customerImplementationDAO;
    }

    /**
     * Functions: RecyclerView.Adapter.
     */

    @Override
    public ViewHolderListCustomer onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(this.layout, parent, false);

        ViewHolderListCustomer viewHolderListCustomer = new ViewHolderListCustomer(view, this.activity, this);

        return viewHolderListCustomer;
    }

    @Override
    public void onBindViewHolder(ViewHolderListCustomer holder, int position) {
        holder.bind(this.listCustomer.get(position), this.onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return this.listCustomer.size();
    }

    /**
     * Functions: Self.
     */

    public void deleteMovie(int position) {
        this.customerImplementationDAO.deleteCustomer(this.listCustomer.get(position));

        this.listCustomer.remove(position);
    }

    public void updateListCustomer(List<Customer> listCustomer) {
        this.listCustomer = listCustomer;
    }
}
