package com.example.shuttleLaunch.home;

import com.example.shuttleLaunch.model.DataSource;
import com.example.shuttleLaunch.model.LaunchResponse;
import com.example.shuttleLaunch.model.RemoteDataSource;

public class HomePresenter implements HomeContract.Presenter, DataSource.DataListener {
    private final HomeContract.View view;
    private final DataSource dataSource;
    public HomePresenter(HomeContract.View view){
        this.view = view;
        dataSource = new RemoteDataSource(this);
    }

    @Override
    public void getLaunchDate(String date) {
        dataSource.getLaunchData(date);
    }

    @Override
    public void loadAllLaunches() {

    }

    @Override
    public void onLaunchRetrieval(LaunchResponse launchResponse) {
        view.showLaunches(launchResponse.getLaunches());
    }

    @Override
    public void onError(Throwable throwable) {
        view.showError(throwable.getMessage());
    }
}
