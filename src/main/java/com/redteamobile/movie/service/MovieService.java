package com.redteamobile.movie.service;

import java.util.List;

import com.redteamobile.movie.model.entity.Movie;
import com.redteamobile.movie.model.req.MovieReq;

import io.swagger.annotations.License;

import com.redteamobile.movie.dao.MovieDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.assertj.core.api.BooleanArrayAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class MovieService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MovieDao iMovieDao;

    public boolean saveOne(MovieReq req) throws Exception {
        iMovieDao.insertOne(this.translateFromReq(req));
        return true;
    }

    public List<Movie> queryAll() throws Exception {
        return iMovieDao.selectMany(Movie.build().isNotNull("id"));
    }
    
    public Movie query(MovieReq req) throws Exception {
        return iMovieDao.select(this.translateFromReq(req));
    }
    
    public List<Movie> queryByName(String name) throws Exception {
        return iMovieDao.selectMany(Movie.build().setName(name).eq("name"));
    }
    
    public Movie query(Movie req) throws Exception {
        return iMovieDao.select(req);
    }
    
    public int updateOne(String name, Integer rating) throws Exception {
        return iMovieDao.updateOne(Movie.build().setName(name).update("rating", rating));
    }
    
    public List<Movie> queryMany(Movie req) throws Exception {
        return iMovieDao.selectMany(req);
    }

    private Movie translateFromReq(MovieReq req) throws Exception {
        Movie entity = Movie.build()
            .setId(req.getId())//no comment
            .setName(req.getName())//no comment
            .setRating(req.getRating())//no comment
        ;
        return entity;
    }
}
