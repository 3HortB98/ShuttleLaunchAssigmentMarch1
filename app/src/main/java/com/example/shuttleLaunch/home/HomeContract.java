package com.example.shuttleLaunch.home;

import com.example.shuttleLaunch.model.Launch;
import com.example.shuttleLaunch.model.LaunchResponse;

import java.util.List;

public interface HomeContract {
    interface View{
        void showLaunches(List<Launch> result);
        void showError(String Message);
    }
    interface Presenter{
        void getLaunchDate(String date);
        void loadAllLaunches();
    }
}
