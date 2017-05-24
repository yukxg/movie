package com.redteamobile.movie.model.req;

import com.google.common.base.MoreObjects;
import java.io.Serializable;

/**
 *
 * no comment
 *
 */
public class MovieReq implements Serializable {

    private Integer id;//no comment
    private String name;//no comment
    private Integer rating;//no comment

    public static MovieReq build() {
        return new MovieReq();
    }
    @Override
    public String toString(){
        return MoreObjects.toStringHelper(this)
                .add("id",id)
                .add("name",name)
                .add("rating",rating)
                .toString();
    }

    public MovieReq setId(Integer id) {
        this.id = id;
        return this;
    }
    public Integer getId() {
        return this.id;
    }

    public MovieReq setName(String name) {
        this.name = name == null ? null : name.trim();
        return this;
    }
    public String getName() {
        return this.name;
    }

    public MovieReq setRating(Integer rating) {
        this.rating = rating;
        return this;
    }
    public Integer getRating() {
        return this.rating;
    }

}

