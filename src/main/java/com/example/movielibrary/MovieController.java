package com.example.movielibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    MovieRepository movieRepository;

    @GetMapping("/")
    public List<Movie> getAll(){
        return movieRepository.getAll();
    }

    @GetMapping("/{id}")
    public Movie GetById(@PathVariable("id") int id){
        return movieRepository.getById(id);
    }

    @PostMapping("/")
    public int add(@RequestBody List<Movie> movies){
        return movieRepository.save(movies);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody Movie updatedMovie){
        Movie movie = movieRepository.getById(id);
        if(movie != null){
            movie.setMovie(updatedMovie.getMovie());
            movie.setRating(updatedMovie.getRating());

            movieRepository.update(movie);

            return 1;
        }else {
            return -1;
        }
    }

    @PatchMapping("/{id}")
    public int updatePatch(@PathVariable("id") int id, @RequestBody Movie updatedMovie){
        Movie movie = movieRepository.getById(id);
        if(movie != null){
           if(updatedMovie.getMovie()!=null){
               movie.setMovie(updatedMovie.getMovie());
           }
           if(updatedMovie.getRating()>0) {
               movie.setRating(updatedMovie.getRating());
           }

            movieRepository.update(movie);

            return 1;
        }else {
            return -1;
        }
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") int id) {
        return movieRepository.delete(id);
    }
}
