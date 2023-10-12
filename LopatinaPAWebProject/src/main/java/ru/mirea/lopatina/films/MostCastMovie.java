package ru.mirea.lopatina.films;


import com.fasterxml.jackson.databind.json.JsonMapper;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class MostCastMovie {

    public static void main(String[] args) throws IOException, SQLException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/prust/wikipedia-movie-data/master/")
                .addConverterFactory(JacksonConverterFactory.create(new JsonMapper()))
                .build();
        MovieService movieService = retrofit.create(MovieService.class);
        Call<List<MovieStructure>> call = movieService.getMovies();
        Response<List<MovieStructure>> response = call.execute();
        MovieStructure MovieAnswer = null;
        int NeedYear = 2000;
        int CastAnswer = 0;
        for (MovieStructure movie : response.body()) {
            if (movie.getYear() < NeedYear && movie.getCast() != null) {
                if (movie.getCast().size() > CastAnswer) {
                    CastAnswer = movie.getCast().size();
                    MovieAnswer = movie;
                }
            }
        }

        if (MovieAnswer != null) {
            System.out.println("Фильм с самым большим количеством актёров до " + NeedYear+ " года: " + MovieAnswer.getTitle());
            System.out.println("Количество актёров: " + CastAnswer);
        } else {
            System.out.println("Фильмы до " + NeedYear+ " года с актёрами не найдены.");
        }
    }
}
