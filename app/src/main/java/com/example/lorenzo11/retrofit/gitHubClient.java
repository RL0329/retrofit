package com.example.lorenzo11.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Lorenzo11 on 05/03/2018.
 */

public interface gitHubClient {

    @GET("/{user}")
    Call<List<GitHubRepo>> reposForUser(
            @Path("user") String user
    );

    @POST("posts")
    Call<GitHubRepo> adduser(@Body GitHubRepo posts);

}
