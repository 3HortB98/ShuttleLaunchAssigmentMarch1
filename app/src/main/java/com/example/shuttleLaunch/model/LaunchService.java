package com.example.shuttleLaunch.model;

import com.example.shuttleLaunch.Constants;
import com.example.shuttleLaunch.model.LaunchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LaunchService {

    @GET(Constants.ENDPOINT)
    Call<LaunchResponse>getLaunchDetail(@Path("launchDate")String date);
}
