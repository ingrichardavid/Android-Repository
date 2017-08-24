package com.example.ing_richardavid.seccion_06_movies.models.entities;

/**
 * Created by ing_richardavid on 24-08-17.
 */

public class Movie {

    /**
     * Objects, variables and constants.
     */

    public static final int LIMIT_QUANTITY = 10;
    public static final int INITIAL_QUANTITY = 0;
    private String name;
    private String description;
    private int quantity;
    private int poster;

    /**
     * Builder.
     * @param name
     * @param description
     * @param quantity
     * @param poster
     */

    public Movie(String name, String description, int quantity, int poster) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.poster = poster;
    }

    /**
     * Getters and Setters.
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
