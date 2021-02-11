package com.gcuello.chiper_movie.data.mapper;

import com.gcuello.chiper_movie.data.db.entities.Cast;
import com.gcuello.chiper_movie.data.db.entities.Crew;
import com.gcuello.chiper_movie.data.db.entities.Genre;
import com.gcuello.chiper_movie.data.db.entities.Movie;
import com.gcuello.chiper_movie.data.db.entities.MovieDetail;
import com.gcuello.chiper_movie.domain.model.DetailMovie;
import com.gcuello.chiper_movie.domain.model.ProductionCompany;
import com.gcuello.chiper_movie.domain.model.ProductionCountry;
import com.gcuello.chiper_movie.domain.model.SpokenLanguage;

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

    @Mapping(target = "id", source = "arg.id")
    @Mapping(target = "backdropPath", source = "arg.backdrop_path")
    @Mapping(target = "belongsToCollection", source = "arg.belongs_to_collection.id")
    @Mapping(target = "budget", source = "arg.budget")
    @Mapping(target = "genres", source = "arg.genres", qualifiedByName = "toStringGenreObject")
    @Mapping(target = "homepage", source = "arg.homepage")
    @Mapping(target = "adult", source = "arg.adult")
    @Mapping(target = "imdbId", source = "arg.imdb_id")
    @Mapping(target = "originalLanguage", source = "arg.original_language")
    @Mapping(target = "originalTitle", source = "arg.original_title")
    @Mapping(target = "overview", source = "arg.overview")
    @Mapping(target = "popularity", source = "arg.popularity")
    @Mapping(target = "posterPath", source = "arg.poster_path")
    @Mapping(target = "productionCompanies", source = "arg.production_companies", qualifiedByName = "toStringProductionCompanies")
    @Mapping(target = "productionCountries", source = "arg.production_countries", qualifiedByName = "toStringProductionCountries")
    @Mapping(target = "releaseDate", source = "arg.release_date")
    @Mapping(target = "revenue", source = "arg.revenue")
    @Mapping(target = "runtime", source = "arg.runtime")
    @Mapping(target = "spokenLanguages", source = "arg.spoken_languages", qualifiedByName = "toStringSpokenLanguages")
    @Mapping(target = "status", source = "arg.status")
    @Mapping(target = "tagline", source = "arg.tagline")
    @Mapping(target = "title", source = "arg.title")
    @Mapping(target = "video", source = "arg.video")
    @Mapping(target = "voteAverage", source = "arg.vote_average")
    @Mapping(target = "voteCount", source = "arg.vote_count")
    MovieDetail toEntity(DetailMovie arg);

    @Mapping(target = "id", source = "arg.id")
    @Mapping(target = "job", source = "arg.job")
    @Mapping(target = "name", source = "arg.name")
    @Mapping(target = "adult", source = "arg.adult")
    @Mapping(target = "gender", source = "arg.gender")
    @Mapping(target = "creditId", source = "arg.credit_id")
    @Mapping(target = "department", source = "arg.department")
    @Mapping(target = "popularity", source = "arg.popularity")
    @Mapping(target = "profilePath", source = "arg.profile_path")
    @Mapping(target = "originalName", source = "arg.original_name")
    @Mapping(target = "knownForDepartment", source = "arg.known_for_department")
    Crew toEntity(com.gcuello.chiper_movie.domain.model.Crew arg);

    @Mapping(target = "id", source = "arg.id")
    @Mapping(target = "castId", source = "arg.cast_id")
    @Mapping(target = "character", source = "arg.character")
    @Mapping(target = "creditId", source = "arg.credit_id")
    @Mapping(target = "gender", source = "arg.gender")
    @Mapping(target = "adult", source = "arg.adult")
    @Mapping(target = "knownForDepartment", source = "arg.known_for_department")
    @Mapping(target = "name", source = "arg.name")
    @Mapping(target = "order", source = "arg.order")
    @Mapping(target = "originalName", source = "arg.original_name")
    @Mapping(target = "popularity", source = "arg.popularity")
    @Mapping(target = "profilePath", source = "arg.profile_path")
    Cast toEntity(com.gcuello.chiper_movie.domain.model.Cast arg);

    @Named("toStringGenreIds")
    default String toStringGenreIds(List<Integer> ids) {
        StringBuilder result = new StringBuilder();
        for (Integer itm : ids) {
            result.append(itm).append(",");
        }
        return result.toString();
    }

    @Named("toStringGenreObject")
    default String toStringGenreObject(List<com.gcuello.chiper_movie.domain.model.Genre> ids) {
        StringBuilder result = new StringBuilder();
        for (com.gcuello.chiper_movie.domain.model.Genre itm : ids) {
            result.append(itm.getId()).append(",");
        }
        return result.toString();
    }

    @Named("toStringProductionCountries")
    default String toStringProductionCountries(List<ProductionCountry> ids) {
        StringBuilder result = new StringBuilder();
        for (ProductionCountry itm : ids) {
            result.append(itm.getName()).append(",");
        }
        return result.toString();
    }

    @Named("toStringProductionCompanies")
    default String toStringProductionCompanies(List<ProductionCompany> ids) {
        StringBuilder result = new StringBuilder();
        for (ProductionCompany itm : ids) {
            result.append(itm.getName()).append(",");
        }
        return result.toString();
    }

    @Named("toStringSpokenLanguages")
    default String toStringSpokenLanguages(List<SpokenLanguage> ids) {
        StringBuilder result = new StringBuilder();
        for (SpokenLanguage itm : ids) {
            result.append(itm.getName()).append(",");
        }
        return result.toString();
    }

    default List<Cast> toList(List<com.gcuello.chiper_movie.domain.model.Cast> items) {
        if (items == null)
            return new ArrayList<>();
        List<Cast> keys = new ArrayList<>();
        for (com.gcuello.chiper_movie.domain.model.Cast item : items)
            keys.add(toEntity(item));
        return keys;
    }

    default List<Crew> toCrewList(List<com.gcuello.chiper_movie.domain.model.Crew> items) {
        if (items == null)
            return new ArrayList<>();
        List<Crew> keys = new ArrayList<>();
        for (com.gcuello.chiper_movie.domain.model.Crew item : items)
            keys.add(toEntity(item));
        return keys;
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
