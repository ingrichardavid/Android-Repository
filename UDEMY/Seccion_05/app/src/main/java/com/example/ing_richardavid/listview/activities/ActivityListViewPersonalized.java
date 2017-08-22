package com.example.ing_richardavid.listview.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ing_richardavid.listview.R;
import com.example.ing_richardavid.listview.activities.adapters.MyAdapter;
import com.example.ing_richardavid.listview.utils.Functions;
import com.example.ing_richardavid.listview.utils.Messages;

import java.util.ArrayList;
import java.util.List;

public class ActivityListViewPersonalized extends AppCompatActivity implements View.OnClickListener {

    /**
     * Objects, variables and constants.
     */

    private List<String> arrayNames;
    private final String titleView = "ListView Personalized";

    /**
     * View components.
     */

    private TextView txtName;
    private Button btnAdd;
    private ListView lVData;
    private Button btnGoToGVPersonalized;
    private MyAdapter myAdapter;
    private Intent intent;

    /**
     * Functions: AppCompatActivity.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_personalized);

        /**
         * Add back button.
         */

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /**
         * Add view title.
         */

        this.getSupportActionBar().setTitle(this.titleView);

        /**
         * Components registering.
         */

        this.txtName = (TextView)findViewById(R.id.activityLVPersonalizedIdTxtVName);

        this.btnAdd = (Button)findViewById(R.id.activityLVPersonalizedIdBtnAdd);
        this.btnAdd.setOnClickListener(this);

        this.arrayNames = new ArrayList<String>();

        this.myAdapter = new MyAdapter(this, R.layout.listview_personalized);

        this.lVData = (ListView)findViewById(R.id.activityLVPersonalizedIdLVData);
        this.lVData.setAdapter(this.myAdapter);

        this.btnGoToGVPersonalized = (Button)findViewById(R.id.activityLVPersonalizedIdGoToGVPersonalized);
        this.btnGoToGVPersonalized.setOnClickListener(this);
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

                this.myAdapter.loadData(this.arrayNames);
                this.myAdapter.notifyDataSetChanged();
            }
        } else if (this.btnGoToGVPersonalized.equals(view)) {
            this.intent = new Intent(this, ActivityGridViewPersonalized.class);

            this.startActivity(this.intent);
        }
    }

    /**
     * Functions: Self.
     */

    private boolean validateFields() {
        if (this.txtName.getText().toString().isEmpty()) {
            Functions.toastShort(this, Messages.ACTIVITY_LISTVIEW_PERSONALIZED_EMPTY_NAME);

            return false;
        }

        return true;
    }

}

