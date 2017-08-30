package com.example.ing_richardavid.seccion_07_sqlite.models.entities;

import com.example.ing_richardavid.seccion_07_sqlite.utils.Functions;

/**
 * Created by ing_richardavid on 25-08-17.
 */

public class Customer {

    /**
     * Objects, variables and constants.
     */

    private int dni;
    private String name;
    private String date_creation;

    /**
     * Builder.
     */

    public Customer(int dni, String name) {
        this.dni = dni;
        this.name = name;
        this.date_creation = Functions.getDateDayMonthYear();
    }

    public Customer(int dni, String name, String date_creation) {
        this.dni = dni;
        this.name = name;
        this.date_creation = date_creation;
    }

    public Customer(int dni) {
        this.dni = dni;
    }

    /**
     * Getters.
     */

    public int getDni() {
        return this.dni;
    }

    public String getName() {
        return this.name;
    }

    public String getDate_creation() { return  this.date_creation; }

}
