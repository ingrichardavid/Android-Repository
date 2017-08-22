package com.example.ing_richardavid.seccion_05_flagsworld.utils;

/**
 * Created by ing_richardavid on 22-08-17.
 */

public enum EnumTypeView {
    LIST_VIEW(0),
    GRID_VIEW(1);

    /**
     * Objects, variables and contants.
     */

    private int typeView;

    /**
     * Builder.
     * @param typeView
     */
    EnumTypeView(int typeView) {
        this.typeView = typeView;
    }

    /**
     * Getters.
     */

    public int getTypeView() {
        return this.typeView;
    }
}
