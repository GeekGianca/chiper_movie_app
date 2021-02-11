package com.gcuello.chiper_movie.data.mapper;

import com.gcuello.chiper_movie.data.db.entities.Genre;
import com.gcuello.chiper_movie.data.db.entities.Movie;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface SharedMapper {
    SharedMapper INSTANCE = Mappers.getMapper(SharedMapper.class);

    @Mapping(target = "id", source = "arg.id")
    @Mapping(target = "name", source = "arg.name")
    @Mapping(target = "type", source = "type")
    Genre toEntity(com.gcuello.chiper_movie.domain.model.Genre arg, String type);

    @Mapping(target = "id", source = "arg.id")
    @Mapping(target = "title", source = "arg.id")
    @Mapping(target = "releaseDate", source = "arg.release_date")
    @Mapping(target = "backdropPath", source = "arg.backdrop_path")
    @Mapping(target = "originalLanguage", source = "arg.original_language")
    @Mapping(target = "genreIds", source = "arg.genre_ids", qualifiedByName = "toStringGenreIds")
    @Mapping(target = "originalTitle", source = "arg.original_title")
    @Mapping(target = "overview", source = "arg.overview")
    @Mapping(target = "popularity", source = "arg.popularity")
    @Mapping(target = "posterPath", source = "arg.poster_path")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "video", source = "arg.video")
    @Mapping(target = "voteAverage", source = "arg.vote_average")
    @Mapping(target = "voteCount", source = "arg.vote_count")
    @Mapping(target = "adult", source = "arg.adult")
    Movie toEntity(com.gcuello.chiper_movie.domain.model.Movie arg, String type);

    @Named("toStringGenreIds")
    default String toStringGenreIds(List<Integer> ids) {
        StringBuilder result = new StringBuilder();
        for (Integer itm : ids) {
            result.append(itm).append(",");
        }
        return result.toString();
    }

    default List<Genre> toList(List<com.gcuello.chiper_movie.domain.model.Genre> items, String type) {
        if (items == null)
            return new ArrayList<>();
        List<Genre> keys = new ArrayList<>();
        for (com.gcuello.chiper_movie.domain.model.Genre item : items)
            keys.add(toEntity(item, type));
        return keys;
    }

    default List<Movie> toMovieList(List<com.gcuello.chiper_movie.domain.model.Movie> items, String type) {
        if (items == null)
            return new ArrayList<>();
        List<Movie> keys = new ArrayList<>();
        for (com.gcuello.chiper_movie.domain.model.Movie item : items)
            keys.add(toEntity(item, type));
        return keys;
    }
}
