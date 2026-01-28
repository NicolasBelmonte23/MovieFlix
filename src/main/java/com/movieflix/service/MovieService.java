package com.movieflix.service;

import com.movieflix.entity.Category;
import com.movieflix.entity.Movie;
import com.movieflix.entity.Streaming;
import com.movieflix.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository  movieRepository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;

    public Movie save(Movie movie){
        movie.setCategories(this.findCategories(movie.getCategories()));
        movie.setStreamings(this.findStreaming(movie.getStreamings()));
        return movieRepository.save(movie);
    }

    public List<Movie> findAll(){
        return movieRepository.findAll();
    }

    public Optional<Movie> findById(Long id){
        return movieRepository.findById(id);
    }

    public List<Movie> findByCategory(Long categoryId) {
        return movieRepository.findByCategoriesId(categoryId);
    }

    public Optional<Movie> update(Long movieId, Movie updateMovie) {
        Optional<Movie> byId = movieRepository.findById(movieId);
        if (byId.isPresent()) {

            List<Category> categories = this.findCategories(updateMovie.getCategories());
            List<Streaming> streaming = this.findStreaming(updateMovie.getStreamings());


            Movie movie = byId.get();
            movie.setTitle(updateMovie.getTitle());
            movie.setDescription(updateMovie.getDescription());
            movie.setRating(updateMovie.getRating());

            movie.getCategories().clear();
            movie.getCategories().addAll(categories);

            movie.getStreamings().clear();
            movie.getStreamings().addAll(streaming);

            movieRepository.save(movie);

            return Optional.of(movie);
        }
        return Optional.empty();
    }

    private List<Category> findCategories(List<Category> categories){
        List<Category> categoriesFound = new ArrayList<>();
        for(Category category : categories){
            categoryService.findById(category.getId()).ifPresent(categoriesFound::add);
        }return categoriesFound;
    }

    private List<Streaming> findStreaming(List<Streaming> streamings){
        List<Streaming> streamingFound = new ArrayList<>();
        for(Streaming streaming : streamings){
            streamingService.findById(streaming.getId()).ifPresent(streamingFound::add);
        }return streamingFound;
    }

    public void deleteMovie(Long id){
        movieRepository.deleteById(id);
    }
}
