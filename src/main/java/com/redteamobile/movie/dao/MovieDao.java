package com.redteamobile.movie.dao;

import org.springframework.stereotype.Repository;
import com.redteamobile.mis.BaseDao;
import com.redteamobile.movie.model.entity.Movie;

@Repository
public class MovieDao extends BaseDao<Movie>{

    @Override
    protected Class<Movie> getEntityClass() {
        return Movie.class;
    }
}
