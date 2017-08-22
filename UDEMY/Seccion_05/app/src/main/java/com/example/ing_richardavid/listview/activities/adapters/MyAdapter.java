package com.example.ing_richardavid.listview.activities.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ing_richardavid.listview.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {

    /**
     * Objects, variables and constants.
     */

    private Context context;
    private int layout;
    private List<String> arrayNames;
    private ViewHolder viewHolder;

    /**
     * View components.
     */

    private LayoutInflater layoutInflater;

    public MyAdapter(Context context, int layout) {
        this.context = context;
        this.layout = layout;
        this.arrayNames = new ArrayList<String>();
    }

    /**
     * Functions: BaseAdapter.
     */

    @Override
    public int getCount() {
        return this.arrayNames.size();
    }

    @Override
    public Object getItem(int i) {
        return this.arrayNames.get(i);
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
            this.viewHolder.txtVName = view.findViewById(R.id.listViewPersonalizedTxtVName);

            view.setTag(this.viewHolder);
        } else {
            this.viewHolder = (ViewHolder)view.getTag();
        }

        this.viewHolder.txtVName.setText(this.arrayNames.get(i));

        return view;
    }

    /**
     * Functions: Self.
     */

    public void loadData(List<String> names) {
        this.arrayNames = names;
    }

    /**
     * Using the pattern ViewHolderPattern.
     */

    private static class ViewHolder {
        private TextView txtVName;
    }
}
