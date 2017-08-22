package com.example.ing_richardavid.seccion_05_flagsworld.models.entities;

/**
 * Created by ing_richardavid on 21-08-17.
 */

public class Country {

    /**
     * Objects, variables and constants.
     */

    private String name;
    private String capital;
    private int icon;

    /**
     * Builder.
     * @param name
     * @param capital
     * @param icon
     */

    public Country(String name, String capital, int icon){
        this.name = name;
        this.capital = capital;
        this.icon = icon;
    }

    /**
     * Getters.
     */

    public String getName() {
        return this.name;
    }

    public String getCapital() {
        return this.capital;
    }

    public int getIcon() {
        return this.icon;
    }

}
