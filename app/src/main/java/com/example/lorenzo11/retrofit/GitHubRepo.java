package com.example.lorenzo11.retrofit;

/**
 * Created by Lorenzo11 on 05/03/2018.
 */

public class GitHubRepo {

    private String id;
    private String username;
    private String password;

    public GitHubRepo(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public String getId() {
        return id;
    }


    public String getUname() {
        return username;
    }

    public String getPword() {
        return password;
    }
}