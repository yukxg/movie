package com.redteamobile.movie.model.entity;

import com.google.common.base.MoreObjects;
import java.io.Serializable;

import com.redteamobile.mis.BasePo;
/**
 *
 * no comment
 *
 */
public class Movie extends BasePo {

    private Integer id;//no comment
    private String name;//no comment
    private Integer rating;//no comment

    public static Movie build() {
        return new Movie();
    }
    @Override
    public String toString(){
        return MoreObjects.toStringHelper(this)
                .add("id",id)
                .add("name",name)
                .add("rating",rating)
                .toString();
    }

    public Movie setId(Integer id) {
        this.id = id;
        return this;
    }
    public Integer getId() {
        return this.id;
    }

    public Movie setName(String name) {
        this.name = name == null ? null : name.trim();
        return this;
    }
    public String getName() {
        return this.name;
    }

    public Movie setRating(Integer rating) {
        this.rating = rating;
        return this;
    }
    public Integer getRating() {
        return this.rating;
    }

}

