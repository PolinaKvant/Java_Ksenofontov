package ru.mirea.lopatina.stonks.until;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.mirea.lopatina.stonks.until.DailyCurs;

public interface StonksService {
    @GET("/scripts/XML_daily.asp")
    Call<DailyCurs> getDailyCurs(@Query("date_req") String date);
}
