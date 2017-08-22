package com.example.ing_richardavid.seccion_05_flagsworld.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.example.ing_richardavid.seccion_05_flagsworld.R;
import com.example.ing_richardavid.seccion_05_flagsworld.adapters.AdapterCountries;
import com.example.ing_richardavid.seccion_05_flagsworld.models.entities.Country;
import com.example.ing_richardavid.seccion_05_flagsworld.utils.EnumTypeView;
import com.example.ing_richardavid.seccion_05_flagsworld.utils.Functions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /**
     * Objects, variables and constants.
     */

    private final String titleView = "Countries";
    private List<Country> countries;
    private int count = 0;
    private String countryUnknown = "Unknown";

    /**
     * View components.
     */

    private ListView lvCountries;
    private GridView gvCountries;
    private AdapterCountries adapterLVCountries;
    private AdapterCountries adapterGVCountries;
    private MenuInflater menuInflater;
    private MenuItem menuOptionList;
    private MenuItem menuOptionGrid;
    private AdapterView.AdapterContextMenuInfo adapterContextMenuInfo;

    /**
     * Functions: AppCompatActivity.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Add icon at status bar.
         */

        this.getSupportActionBar().setIcon(R.mipmap.ic_myicon);
        this.getSupportActionBar().setDisplayUseLogoEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);

        /**
         * Add title at status bar.
         */

        this.getSupportActionBar().setTitle(this.titleView);

        /**
         * Components registering.
         */

        this.countries = new ArrayList<Country>(){{
            add(new Country("Venezuela", "Caracas", R.mipmap.ic_venezuela));
            add(new Country("Colombia", "Bogotá", R.mipmap.ic_colombia));
            add(new Country("Chile", "Santiago", R.mipmap.ic_chile));
            add(new Country("Argentina", "Buenos Aires", R.mipmap.ic_argentina));
            add(new Country("Ecuador", "Quito", R.mipmap.ic_ecuador));
            add(new Country("Puerto Rico", "San Juan", R.mipmap.ic_puerto_rico));
        }};

        this.adapterLVCountries = new AdapterCountries(this, R.layout.layout_list_view, EnumTypeView.LIST_VIEW);
        this.adapterLVCountries.loadData(this.countries);
        this.adapterLVCountries.notifyDataSetChanged();

        this.adapterGVCountries = new AdapterCountries(this, R.layout.layout_grid_view, EnumTypeView.GRID_VIEW);
        this.adapterGVCountries.loadData(this.countries);
        this.adapterGVCountries.notifyDataSetChanged();

        this.lvCountries = (ListView)findViewById(R.id.mainActivityIdLVCountries);
        this.lvCountries.setAdapter(this.adapterLVCountries);

        this.gvCountries = (GridView)findViewById(R.id.mainActivityIdGVCountries);
        this.gvCountries.setAdapter(this.adapterGVCountries);

        this.listViewOrGridView(EnumTypeView.GRID_VIEW);

        this.registerForContextMenu(this.lvCountries);
        this.registerForContextMenu(this.gvCountries);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menuInflater = getMenuInflater();
        this.menuInflater.inflate(R.menu.menu_options, menu);

        this.menuOptionList = menu.findItem(R.id.menuOptionsIdList);

        this.menuOptionGrid = menu.findItem(R.id.menuOptionsIdGrid);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuOptionsIdAdd:
                this.countries.add(this.getCountryUnknown());

                this.adapterLVCountries.notifyDataSetChanged();

                this.adapterGVCountries.notifyDataSetChanged();

                return true;
            case R.id.menuOptionsIdList:
                this.listViewOrGridView(EnumTypeView.GRID_VIEW);

                return true;
            case R.id.menuOptionsIdGrid:
                this.listViewOrGridView(EnumTypeView.LIST_VIEW);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        this.adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo)menuInfo;

        if (this.countries.get(this.adapterContextMenuInfo.position).getName().equals(this.countryUnknown)) {
            menu.setHeaderTitle(this.countries.get(this.adapterContextMenuInfo.position).getName() + " - " + this.countries.get(this.adapterContextMenuInfo.position).getCapital());
        } else {
            menu.setHeaderTitle(this.countries.get(this.adapterContextMenuInfo.position).getName());
        }

        this.menuInflater = getMenuInflater();
        this.menuInflater.inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        this.adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.menuContextIdDelete:
                this.countries.remove(this.adapterContextMenuInfo.position);

                this.adapterLVCountries.notifyDataSetChanged();

                this.adapterGVCountries.notifyDataSetChanged();
            default:
                return super.onContextItemSelected(item);
        }
    }

    /**
     * Functions: Self.
     */

    private void listViewOrGridView(EnumTypeView enumTypeView) {
        if (enumTypeView.getTypeView() == EnumTypeView.LIST_VIEW.getTypeView()) {
            this.lvCountries.setVisibility(View.INVISIBLE);

            if (this.menuOptionList != null) this.menuOptionList.setVisible(true);

            this.gvCountries.setVisibility(View.VISIBLE);

            if (this.menuOptionGrid != null) this.menuOptionGrid.setVisible(false);
        } else if (enumTypeView.getTypeView() == EnumTypeView.GRID_VIEW.getTypeView()) {
            this.lvCountries.setVisibility(View.VISIBLE);

            if (this.menuOptionList != null) this.menuOptionList.setVisible(false);

            this.gvCountries.setVisibility(View.INVISIBLE);

            if (this.menuOptionGrid != null) this.menuOptionGrid.setVisible(true);
        }
    }

    private Country getCountryUnknown() {
        return new Country(this.countryUnknown, "Added Nº " + ++this.count, R.mipmap.ic_question);
    }
}
