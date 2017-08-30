package com.example.ing_richardavid.seccion_07_sqlite.models.entities;

/**
 * Created by ing_richardavid on 29-08-17.
 */

public class Product {

    /**
     * Objects, variables and constants.
     */

    private int id;
    private String name;
    private int stock;

    /**
     * Builder.
     */

    public Product(int id, String name, int stock) {
        this.id = id;
        this.name = name;
        this.stock = stock;
    }

    /**
     * Getters.
     */

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getStock() {
        return this.stock;
    }

}
