package com.example.lorenzo11.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Lorenzo11 on 05/03/2018.
 */

public interface gtiHubClient {

 //   @GET("/users/{user}/repos")
    @GET("/{user}")
    Call<List<GitHubRepo>> reposForUser(
            @Path("user") String user
    );
//    Call<List<GitHubRepo>> reposForUser(
//            @Path("user") String user
//    );
}
