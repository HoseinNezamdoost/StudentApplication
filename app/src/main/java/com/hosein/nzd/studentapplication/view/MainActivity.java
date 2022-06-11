package com.hosein.nzd.studentapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.hosein.nzd.studentapplication.R;
import com.hosein.nzd.studentapplication.database.AppDatabase;
import com.hosein.nzd.studentapplication.model.ApiServiceProvider;
import com.hosein.nzd.studentapplication.viewmodel.ViewModelMain;
import com.hosein.nzd.studentapplication.viewmodel.ViewModelMainFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewModelMain viewModelMain = new ViewModelProvider(this , new ViewModelMainFactory(ApiServiceProvider.getApiService()
                , AppDatabase.getAppDatabase(getApplicationContext()).studentDao())).get(ViewModelMain.class);

        viewModelMain.getGetStudent().observe(this, students -> {
            Log.i(TAG, "onCreate: ");
        });

        viewModelMain.getError().observe(this, error -> {
            Log.e(TAG, "onCreate: " + error);
        });

    }
}