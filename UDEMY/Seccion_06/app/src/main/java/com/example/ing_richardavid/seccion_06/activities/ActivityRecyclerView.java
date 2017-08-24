package com.example.ing_richardavid.seccion_06.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ing_richardavid.seccion_06.R;
import com.example.ing_richardavid.seccion_06.adapters.AdapterListName;
import com.example.ing_richardavid.seccion_06.adapters.interfaces.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class ActivityRecyclerView extends AppCompatActivity implements OnItemClickListener, View.OnClickListener {

    /**
     * Objects, variables and contants.
     */

    private List<String> listNames;
    private final String titleView = "Simple RecyclerView";
    private int counter = 0;

    /**
     * View components.
     */

    private RecyclerView rvListNames;
    private RecyclerView.Adapter rvAListNames;
    private RecyclerView.LayoutManager rvLListNames;
    private MenuInflater menuInflater;
    private Button btnCardView;
    private Intent intent;

    /**
     * Functions: AppCompatActivity.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Add title view.
         */

        this.getSupportActionBar().setTitle(this.titleView);

        /**
         * Components registering.
         */

        this.listNames = new ArrayList<String>() {{
            add("Luis");
            add("Juan");
            add("David");
            add("Karen");
            add("Robert");
            add("José");
            add("Alejandro");
            add("Miguel");
            add("Christian");
        }};

        this.rvLListNames = new LinearLayoutManager(this);
        //this.rvLListNames = new GridLayoutManager(this, 2);

        //When using StaggeredGridLayoutManager, disable the setHasFixedSize option.

        //this.rvLListNames = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        this.rvAListNames = new AdapterListName(this.listNames, R.layout.layout_list_name, this);

        this.rvListNames = (RecyclerView)findViewById(R.id.activityMainIdRVNames);
        this.rvListNames.setAdapter(this.rvAListNames);
        this.rvListNames.setLayoutManager(this.rvLListNames);
        this.rvListNames.setHasFixedSize(true);
        this.rvListNames.setItemAnimator(new DefaultItemAnimator());

        this.btnCardView = (Button)findViewById(R.id.activityMainIdBtnCV);
        this.btnCardView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (this.btnCardView.equals(view)) {
            this.intent = new Intent(this, ActivityCardView.class);

            this.startActivity(this.intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menuInflater = getMenuInflater();
        this.menuInflater.inflate(R.menu.menu_options, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuOptionsIdAddItem:
                this.addNameToList(0);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Functions: OnItemClickListener.
     */

    @Override
    public void onItemClick(Object object, int position) {
        this.deleteNameToList(position);
    }

    /**
     * Functions: Self.
     */

    private void addNameToList(int position) {
        this.listNames.add(position, "New name: " + ++counter);

        this.rvAListNames.notifyItemInserted(position);

        this.rvLListNames.scrollToPosition(position);
    }

    private void deleteNameToList(int position) {
        this.listNames.remove(position);

        this.rvAListNames.notifyItemRemoved(position);
    }
}
