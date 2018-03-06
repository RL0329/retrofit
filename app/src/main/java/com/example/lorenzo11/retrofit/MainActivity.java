package com.example.lorenzo11.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ListView Lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Lv = findViewById(R.id.lv);

//        String API_BASE_URL = "https://api.github.com/";
        String API_BASE_URL = "http://10.16.33.68:3000";

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.client(httpClient.build()).build();

        gtiHubClient client =  retrofit.create(gtiHubClient.class);
//        Call<List<GitHubRepo>> call = client.reposForUser("fs-opensource");
        Call<List<GitHubRepo>> call = client.reposForUser("posts");

        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {

                List<GitHubRepo> repos = response.body();

                Lv.setAdapter( new GitHubRepoAdapter(MainActivity.this, repos));


            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {

                Toast.makeText(MainActivity.this,"error",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
