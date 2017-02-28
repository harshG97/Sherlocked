package com.example.harsh.sherlocked.extra;

/**
 * Created by Harsh on 25-12-2016.
 */

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface2 {


    @GET("tv/19885/season/1")
    Call<EpisodeResponse> getEpisodeList( @Query("api_key") String apiKey);
}


