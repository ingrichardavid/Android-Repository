package com.example.ing_richardavid.seccion_07_sqlite.models.entities;

/**
 * Created by ing_richardavid on 29-08-17.
 */

public class Products {

    /**
     * Object, variables and constants.
     */

    private int id;
    private int id_customer;
    private int id_product;
    private int quantity;

    /**
     * Builder.
     */

    public Products(int id, int id_customer, int id_product, int quantity) {
        this.id = id;
        this.id_customer = id_customer;
        this.id_product = id_product;
        this.quantity = quantity;
    }

    /**
     * Getters.
     */

    public int getId() {
        return this.id;
    }

    public int getId_customer() {
        return this.id_customer;
    }

    public int getId_product() {
        return this.id_product;
    }

    public int getQuantity() {
        return this.getQuantity();
    }

}

