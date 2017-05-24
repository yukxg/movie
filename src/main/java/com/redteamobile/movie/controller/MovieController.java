package com.redteamobile.movie.controller;

import com.redteamobile.movie.service.MovieService;
import com.redteamobile.movie.model.req.MovieReq;
import com.redteamobile.movie.dao.MovieDao;
import com.redteamobile.movie.model.entity.Movie;
import com.redteamobile.movie.model.page.ResponseStruct;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Movie")
@Api(description = "Movie相关操作")
public class MovieController extends BaseController {

    @Autowired
    private MovieService iMovieService;
    @Autowired
    private MovieDao iMovieDao;

    @RequestMapping(value = "/all", method = RequestMethod.POST)
    @ApiOperation(value = "获取全部Movie的接口", response = MovieReq.class)
    public ResponseStruct getMovies() throws Exception {
        return succ(iMovieService.queryAll());
    }

    @RequestMapping(value = "/queryByName", method = RequestMethod.POST)
    @ApiOperation(value = "根据name查找movie信息", response = MovieReq.class)
    public ResponseStruct getMovie(
        @RequestBody String name
    ) throws Exception {
        return succ(iMovieService.queryByName(name));
    
    }
    
    @RequestMapping(value = "/updateRatingByName", method = RequestMethod.POST)
    @ApiOperation(value = "update Movie rating", response = MovieReq.class)
    public ResponseStruct getMovie(
        @RequestParam String name,
        @RequestParam Integer rating
    ) throws Exception {
        return succ(iMovieDao.updateOne(Movie.build().setName(name).update("rating", rating)));
    
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "添加一个Movie", response = MovieReq.class)
    public ResponseStruct addMovie(
             @RequestBody MovieReq req
    ) throws Exception {
        iMovieService.saveOne(req);
        return succ();
    }
}
