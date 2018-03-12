package com.example.lorenzo11.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class addUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        final EditText eMailEt = findViewById(R.id.eMialEt);
        final EditText uNameEt = findViewById(R.id.uNameEt);
        final EditText pWordEt = findViewById(R.id.pWordEt);
        final EditText fNameEt = findViewById(R.id.fNameEt);
        final EditText lNameEt = findViewById(R.id.lNameEt);
        final Button addBtn = findViewById(R.id.addUserBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GitHubRepo newuser = new GitHubRepo(

                        eMailEt.getText().toString(),
                        uNameEt.getText().toString(),
                        pWordEt.getText().toString(),
                        fNameEt.getText().toString(),
                        lNameEt.getText().toString()
                );

                if(uNameEt.getText().toString().isEmpty()){
                    Toast.makeText(addUser.this,"please enter a username.",Toast.LENGTH_SHORT).show();
                }
                else if (pWordEt.getText().toString().isEmpty()){
                    Toast.makeText(addUser.this,"please enter a password.",Toast.LENGTH_SHORT).show();
                }
                else {
                    sendNetwordRequest(newuser);
                    finish();
                }
            }
        });
    }

    private void sendNetwordRequest(GitHubRepo newuser) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://10.16.33.79:3000")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.client(httpClient.build()).build();

        gitHubClient client = retrofit.create(gitHubClient.class);
        Call<GitHubRepo> call = client.adduser(newuser);

        call.enqueue(new Callback<GitHubRepo>() {
            @Override
            public void onResponse(Call<GitHubRepo> call, Response<GitHubRepo> response) {
//                Toast.makeText(addUser.this,response.body().getId(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<GitHubRepo> call, Throwable t) {
                Toast.makeText(addUser.this,"error",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
