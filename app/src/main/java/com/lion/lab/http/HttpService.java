package com.lion.lab.http;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface HttpService {

    @GET("users/{user}/repos")
    Observable<List<Repository>> getRepositories(@Path("user") String user);

    @GET("repos/{owner}/{repo}/labels")
    Observable<List<Label>> getLabels(@Path("owner") String owner,
                                      @Path("repo") String repo);
}
