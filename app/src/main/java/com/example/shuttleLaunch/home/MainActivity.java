package com.example.shuttleLaunch.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shuttleLaunch.R;
import com.example.shuttleLaunch.model.Launch;
import com.example.shuttleLaunch.model.LaunchAdapter;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements HomeContract.View {
    EditText etYear;
    EditText etMonth;
    EditText etDay;
    Button btnSearch;
    LaunchAdapter launchAdapter = new LaunchAdapter();
    private HomeContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etYear = findViewById(R.id.etYear);
        etMonth = findViewById(R.id.etMonth);
        etDay = findViewById(R.id.etDay);
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
                int yearNum = Integer.parseInt(etYear.getText().toString());
                String year = String.format("%04d",yearNum);
                int monthNum = Integer.parseInt(etMonth.getText().toString());
                String month = String.format("%02d",monthNum);
                int dayNum = Integer.parseInt(etDay.getText().toString());
                String day =  String.format("%02d",dayNum);
                final String date = year+"-"+month+"-"+day;
                //final String date =constructdate;
                if(isThisDateValid(date)){
                    presenter.getLaunchDate(date);
                }else{
                    Toast.makeText(getApplicationContext(),"Please enter a valid date",Toast.LENGTH_LONG).show();
                }



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


    private boolean isThisDateValid(String dateToValidate){

        if(dateToValidate == null){
            return false;
        }
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setLenient(false);

        try {

            //if not valid, it will throw ParseException
            Date date = sdf.parse(dateToValidate);


        } catch (ParseException e) {

            e.printStackTrace();
            return false;
        }

        return true;
    }
}
