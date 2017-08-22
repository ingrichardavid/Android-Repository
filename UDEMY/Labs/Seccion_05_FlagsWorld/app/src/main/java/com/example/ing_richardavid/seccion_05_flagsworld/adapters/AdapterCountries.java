package com.example.ing_richardavid.seccion_05_flagsworld.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ing_richardavid.seccion_05_flagsworld.R;
import com.example.ing_richardavid.seccion_05_flagsworld.models.entities.Country;
import com.example.ing_richardavid.seccion_05_flagsworld.utils.EnumTypeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ing_richardavid on 22-08-17.
 */

public class AdapterCountries extends BaseAdapter {

    /**
     * Objects, variables and constants.
     */

    private Context context;
    private int layout;
    private List<Country> countries;
    private ViewHolder viewHolder;
    private EnumTypeView enumTypeView;

    /**
     * View components.
     */

    private LayoutInflater layoutInflater;

    /**
     * Builder.
     */

    public AdapterCountries(Context context, int layout, EnumTypeView enumTypeView) {
        this.context = context;
        this.layout = layout;
        this.countries = new ArrayList<Country>();
        this.enumTypeView = enumTypeView;
    }

    /**
     * Functions: BaseAdapter.
     */

    @Override
    public int getCount() {
        return this.countries.size();
    }

    @Override
    public Object getItem(int i) {
        return this.countries.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            this.layoutInflater = LayoutInflater.from(this.context);

            view = this.layoutInflater.inflate(this.layout, null);

            this.viewHolder = new ViewHolder();

            if (this.enumTypeView.getTypeView() == EnumTypeView.LIST_VIEW.getTypeView()) {
                this.viewHolder.txtVName = view.findViewById(R.id.layoutLVIdTVName);
                this.viewHolder.txtCapital = view.findViewById(R.id.layoutLVIdTVCapital);
                this.viewHolder.imgVIcon = view.findViewById(R.id.layoutLVIdIVIcon);
            } else if (this.enumTypeView.getTypeView() == EnumTypeView.GRID_VIEW.getTypeView()) {
                this.viewHolder.txtVName = view.findViewById(R.id.layoutGVIdTVName);
                this.viewHolder.txtCapital = view.findViewById(R.id.layoutGVIdTVCapital);
                this.viewHolder.imgVIcon = view.findViewById(R.id.layoutGVIdIVIcon);
            }

            view.setTag(this.viewHolder);
        } else {
            this.viewHolder = (ViewHolder)view.getTag();
        }

        this.viewHolder.txtVName.setText(this.countries.get(i).getName());
        this.viewHolder.txtCapital.setText(this.countries.get(i).getCapital());
        this.viewHolder.imgVIcon.setImageResource(this.countries.get(i).getIcon());

        return view;
    }

    /**
     * ViewHolder pattern.
     */

    private static class ViewHolder {
        private ImageView imgVIcon;
        private TextView txtVName;
        private TextView txtCapital;
    }

    /**
     * Functions: Self.
     */

    public void loadData(List<Country> countries) {
        this.countries = countries;
    }

    public void setEnumTypeView(EnumTypeView enumTypeView) {
        this.enumTypeView = enumTypeView;
    }
}
