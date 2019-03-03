package com.example.shuttleLaunch.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shuttleLaunch.R;
import com.example.shuttleLaunch.model.Launch;
import com.example.shuttleLaunch.model.LaunchAdapter;
import com.example.shuttleLaunch.model.LaunchResponse;

import java.util.List;

public class MainActivity extends AppCompatActivity implements HomeContract.View {
    TextView etInput;
    Button btnSearch;
    LaunchAdapter launchAdapter = new LaunchAdapter();
    private HomeContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etInput = findViewById(R.id.etInputDate);
        btnSearch = findViewById(R.id.btnSearch);

        RecyclerView recyclerView = findViewById(R.id.rvData);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration divider = new DividerItemDecoration(this,linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(divider);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(launchAdapter);
        presenter = new HomePresenter(this);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    presenter.getLaunchDate(etInput.getText().toString());

            }
        });


    }

    @Override
    public void showLaunches(List<Launch> result) {
        launchAdapter.setData(result);
    }

    @Override
    public void showError(String Message) {
        Toast.makeText(this, Message, Toast.LENGTH_LONG).show();
    }
}
