package com.example.ing_richardavid.listview.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.ing_richardavid.listview.R;
import com.example.ing_richardavid.listview.activities.adapters.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class ActivityDynamicGridView extends AppCompatActivity {

    /**
     * Objects, variables and constants.
     */

    private List<String> arrayNames;
    private final String titleView = "Dynamic GridView";
    private int counter = 0;

    /**
     * View components.
     */

    private GridView gVData;
    private MyAdapter myAdapter;
    private MenuInflater menuInflater;
    private AdapterView.AdapterContextMenuInfo adapterContextMenuInfo;

    /**
     * Functions: AppCompatActivity.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_grid_view);

        /**
         * Add buttom back.
         */

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /**
         * Add view title.
         */

        this.getSupportActionBar().setTitle(this.titleView);

        /**
         * Components registering.
         */

        this.arrayNames = new ArrayList<String>();

        this.myAdapter = new MyAdapter(this, R.layout.listview_personalized);

        this.gVData = (GridView)findViewById(R.id.activityDynamicGVIdGVData);
        this.gVData.setAdapter(this.myAdapter);

        this.registerForContextMenu(this.gVData);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menuInflater = getMenuInflater();
        this.menuInflater.inflate(R.menu.menu_action_add, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuActionAddItem:
                this.arrayNames.add("Added Nº " + (++this.counter));

                this.myAdapter.loadData(this.arrayNames);
                this.myAdapter.notifyDataSetChanged();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        this.adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo)menuInfo;

        menu.setHeaderTitle(this.arrayNames.get(this.adapterContextMenuInfo.position));

        this.menuInflater = getMenuInflater();
        this.menuInflater.inflate(R.menu.menu_context_delete, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        this.adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.menuContextDeleteItem:
                this.arrayNames.remove(this.adapterContextMenuInfo.position);

                this.myAdapter.notifyDataSetChanged();

                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
