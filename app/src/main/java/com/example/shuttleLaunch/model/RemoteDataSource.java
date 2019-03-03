package com.example.shuttleLaunch.model;

import com.example.shuttleLaunch.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource implements DataSource {
   private final DataSource.DataListener listener;
   private final LaunchService launchService;

   public RemoteDataSource(DataSource.DataListener listener){
       this.listener = listener;

       OkHttpClient okHttpClient = new OkHttpClient.Builder()
               .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
               .build();

       Retrofit retrofit = new Retrofit.Builder()
               .baseUrl(Constants.BASE_URL)
               .addConverterFactory(GsonConverterFactory.create())
               .client(okHttpClient)
               .build();

       launchService = retrofit.create(LaunchService.class);
   }
    @Override
    public void getLaunchData(String date) {
        launchService.getLaunchDetail(date).enqueue(new Callback<LaunchResponse>() {
            @Override
            public void onResponse(Call<LaunchResponse> call, Response<LaunchResponse> response) {
                if(response.isSuccessful()){
                    listener.onLaunchRetrieval(response.body());
                }
            }

            @Override
            public void onFailure(Call<LaunchResponse> call, Throwable t) {
                listener.onError(t);
            }
        });
    }

}
