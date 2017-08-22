package com.example.ing_richardavid.listview.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ing_richardavid.listview.R;
import com.example.ing_richardavid.listview.utils.Functions;
import com.example.ing_richardavid.listview.utils.Messages;

import java.util.ArrayList;
import java.util.List;

public class ActivityListView extends AppCompatActivity implements View.OnClickListener {

    /**
     * Objects, variables and constants.
     */

    private List<String> arrayNames;

    /**
     * View components.
     */

    private TextView txtName;
    private Button btnAdd;
    private ListView lVData;
    private ArrayAdapter<String> arrayAdapter;
    private Button btnGoToLVPersonalized;
    private Intent intent;

    /**
     * Functions: AppCompataActivity.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        /**
         * Add icon at status bar.
         */

        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.getSupportActionBar().setIcon(R.mipmap.ic_myicon);

        /**
         * Components registering.
         */

        this.txtName = (TextView)findViewById(R.id.activityLVIdTxtVName);

        this.btnAdd = (Button)findViewById(R.id.activityLVIdBtnAdd);
        this.btnAdd.setOnClickListener(this);

        this.arrayNames = new ArrayList<String>();

        this.arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, this.arrayNames);

        this.lVData = (ListView)findViewById(R.id.activityLVIdLVData);
        this.lVData.setAdapter(this.arrayAdapter);

        this.btnGoToLVPersonalized = (Button)findViewById(R.id.activityLVIdBtnGoToLVPersonalized);
        this.btnGoToLVPersonalized.setOnClickListener(this);
    }

    /**
     * Functions: OnClickListener.
     */
    @Override
    public void onClick(View view) {
        if (this.btnAdd.equals(view)) {
            if (this.validateFields()) {
                this.arrayNames.add(this.txtName.getText().toString());

                this.txtName.setText("");

                this.arrayAdapter.notifyDataSetChanged();
            }
        } else if (this.btnGoToLVPersonalized.equals(view)) {
            this.intent = new Intent(this, ActivityListViewPersonalized.class);

            this.startActivity(this.intent);
        }
    }

    /**
     * Functions: Self.
     */

    private boolean validateFields() {
        if (this.txtName.getText().toString().isEmpty()) {
            Functions.toastShort(this, Messages.ACTIVITY_LISTVIEW_EMPTY_NAME);

            return false;
        }

        return true;
    }

}