package com.example.shuttleLaunch.model;

public interface DataSource {
    void getLaunchData(String date);

    interface DataListener{
        void onLaunchRetrieval(LaunchResponse launchResponse);
        void onError(Throwable throwable);
    }
}
