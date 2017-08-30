package com.example.ing_richardavid.seccion_07_sqlite.activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ing_richardavid.seccion_07_sqlite.R;
import com.example.ing_richardavid.seccion_07_sqlite.activities.adapters.AdapterListCustomers;
import com.example.ing_richardavid.seccion_07_sqlite.activities.adapters.interfaces.OnItemClickListener;
import com.example.ing_richardavid.seccion_07_sqlite.models.entities.Customer;
import com.example.ing_richardavid.seccion_07_sqlite.models.implementions.CustomerImplementationDAO;
import com.example.ing_richardavid.seccion_07_sqlite.utils.Functions;
import com.example.ing_richardavid.seccion_07_sqlite.utils.Messages;

import java.util.List;

public class ActivityMain extends AppCompatActivity implements OnItemClickListener, View.OnClickListener {

    /**
     * Objects, variables and constants.
     */

    private final String titleView = "Customers";
    private List<Customer> listCustomer;
    private Customer customer;

    /**
     * View components.
     */

    private TextView txtVName;
    private TextView txtVDni;
    private Button btnAdd;
    private RecyclerView recyclerView;
    private AdapterListCustomers recyclerViewAdapter;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;
    private CustomerImplementationDAO customerImplementationDAO;
    private MenuItem menuItemUpdateCustomer;

    /**
     * Functions: AppCompatActivity.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Add icon and title view.
         */

        this.getSupportActionBar().setIcon(R.mipmap.ic_myicon);
        this.getSupportActionBar().setDisplayUseLogoEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.getSupportActionBar().setTitle(this.titleView);

        /**
         * Components registering.
         */

        this.customerImplementationDAO = CustomerImplementationDAO.getInstance(this);

        this.listCustomer = this.customerImplementationDAO.getCustomer();

        this.txtVDni = (TextView)findViewById(R.id.activityMainIdTxtVDni);

        this.txtVName = (TextView)findViewById(R.id.activityMainIdTxtVName);

        this.btnAdd = (Button)findViewById(R.id.activityMainIdBtnAdd);
        this.btnAdd.setOnClickListener(this);

        this.recyclerViewLayoutManager = new LinearLayoutManager(this);

        this.recyclerViewAdapter = new AdapterListCustomers(this, this.listCustomer, R.layout.layout_customer_list, this, this.customerImplementationDAO);

        this.recyclerView = (RecyclerView)findViewById(R.id.activityMainIdRVCustomer);
        this.recyclerView.setAdapter(this.recyclerViewAdapter);
        this.recyclerView.setLayoutManager(this.recyclerViewLayoutManager);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_options, menu);

        this.menuItemUpdateCustomer = menu.findItem(R.id.menuOptionsIdUpdate);
        this.menuItemUpdateCustomer.setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuOptionsIdUpdate:
                if (this.validateFields()) {
                    this.customer = new Customer(
                            Integer.valueOf(this.txtVDni.getText().toString()),
                            this.txtVName.getText().toString()
                    );

                    if (this.customerImplementationDAO.updateCustomer(this.customer)) {
                        Functions.toastShort(this, Messages.ACTIVITY_MAIN_CUSTOMER_UPDATE);

                        this.recyclerViewAdapter.updateListCustomer(this.customerImplementationDAO.getCustomer());
                        this.recyclerViewAdapter.notifyDataSetChanged();
                    } else {
                        Functions.toastShort(this, Messages.ACTIVITY_MAIN_CUSTOMER_EXIST);
                    }

                    this.restartActivity();
                }

                return true;
            case R.id.menuOptionIdRestart:
                this.restartActivity();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Functions: OnClickListener.
     */

    @Override
    public void onClick(View view) {
        if (this.btnAdd.equals(view)) {
            if (this.validateFields()) {
                this.customer = new Customer(
                        Integer.valueOf(this.txtVDni.getText().toString()),
                        this.txtVName.getText().toString()
                );

                if (this.customerImplementationDAO.insertCustomer(this.customer)) {
                    Functions.toastShort(this, Messages.ACTIVITY_MAIN_CREATE_CUSTOMER);

                    this.addCustomer(this.customer);
                } else {
                    Functions.toastShort(this, Messages.ACTIVITY_MAIN_CUSTOMER_EXIST);
                }

                this.restartActivity();
            }
        }
    }

    /**
     * Functions: OnItemClickListener
     */

    @Override
    public void onItemClick(Object object, int position) {
        this.txtVDni.setText(String.valueOf(((Customer) object).getDni()));
        this.txtVDni.setEnabled(false);

        this.txtVName.setText(((Customer) object).getName());
        this.txtVName.requestFocus();

        this.enableOrDisableBtnAdd(false);

        this.menuItemUpdateCustomer.setVisible(true);
    }

    /**
     * Functions: Self.
     */

    private void restartActivity() {
        this.txtVDni.setText("");
        this.txtVDni.setEnabled(true);
        this.txtVDni.requestFocus();

        this.txtVName.setText("");

        this.enableOrDisableBtnAdd(true);

        this.menuItemUpdateCustomer.setVisible(false);
    }

    private boolean validateFields() {
        if (this.txtVDni.getText().toString().isEmpty()) {
            Functions.toastShort(this, Messages.ACTIVITY_MAIN_EMPTY_DNI);

            this.txtVDni.requestFocus();

            return false;
        } else if (this.txtVName.getText().toString().isEmpty()) {
            Functions.toastShort(this, Messages.ACTIVITY_MAIN_EMPTY_NAME);

            this.txtVName.requestFocus();

            return false;
        }

        return true;
    }

    private void addCustomer(Customer customer) {
        this.listCustomer.add(customer);

        this.recyclerViewAdapter.notifyItemInserted(this.listCustomer.size() - 1);

        this.recyclerView.scrollToPosition(this.listCustomer.size() - 1);
    }

    private void enableOrDisableBtnAdd(boolean enable) {
        if (enable) {
            this.btnAdd.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            this.btnAdd.setTextColor(Color.WHITE);
        } else {
            this.btnAdd.setBackgroundColor(Color.LTGRAY);
            this.btnAdd.setTextColor(Color.DKGRAY);
        }

        this.btnAdd.setEnabled(enable);
    }
}
