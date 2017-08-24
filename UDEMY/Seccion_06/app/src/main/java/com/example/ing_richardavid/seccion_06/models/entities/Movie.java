package com.example.ing_richardavid.seccion_06.models.entities;

/**
 * Created by ing_richardavid on 23-08-17.
 */

public class Movie {

    /**
     * Objects, variables and contants.
     */

    private String name;
    private int poster;

    /**
     * Builder.
     * @param name
     * @param poster
     */

    public Movie(String name, int poster) {
        this.name = name;
        this.poster = poster;
    }

    /**
     * Getters and Setters.
     */

    public String getName() {
        return this.name;
    }

    public int getPoster() {
        return this.poster;
    }

}
