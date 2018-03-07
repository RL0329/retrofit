package com.example.lorenzo11.retrofit;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
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
    private Button nextPageBtn;
    private SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Lv = findViewById(R.id.lv);
        nextPageBtn = findViewById(R.id.nextBtn);
        swipeRefreshLayout = findViewById(R.id.swipeLayout);

        nextPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i =new Intent(MainActivity.this,addUser.class);
                startActivity(i);

            }
        });

//        loadData();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
                swipeRefreshLayout.setRefreshing(false);

            }
        });


    }

    private void loadData(){

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://10.20.110.30:3000")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.client(httpClient.build()).build();

        gitHubClient client =  retrofit.create(gitHubClient.class);
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
