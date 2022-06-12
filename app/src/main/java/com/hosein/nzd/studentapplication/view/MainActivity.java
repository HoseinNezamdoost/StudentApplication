package com.hosein.nzd.studentapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hosein.nzd.studentapplication.R;
import com.hosein.nzd.studentapplication.adapter.AdapterStudent;
import com.hosein.nzd.studentapplication.database.AppDatabase;
import com.hosein.nzd.studentapplication.model.ApiServiceProvider;
import com.hosein.nzd.studentapplication.model.StudentRepository;
import com.hosein.nzd.studentapplication.viewmodel.ViewModelMain;
import com.hosein.nzd.studentapplication.viewmodel.ViewModelFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewModelMain viewModelMain = new ViewModelProvider(this, new ViewModelFactory(new StudentRepository(ApiServiceProvider.getApiService()
                , AppDatabase.getAppDatabase(getApplicationContext()).studentDao()))).get(ViewModelMain.class);

        viewModelMain.getStudent().observe(this, students -> {
            RecyclerView recyclerView = findViewById(R.id.recycler_student);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));
            AdapterStudent adapterStudent = new AdapterStudent(students);
            recyclerView.setAdapter(adapterStudent);
        });

        viewModelMain.getError().observe(this, error -> {
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        });

        viewModelMain.getProgressbar().observe(this, progressMustShow -> {
            findViewById(R.id.progress).setVisibility(progressMustShow ? View.VISIBLE : View.GONE);
        });

        viewModelMain.getToolbarText().observe(this , s -> {
            TextView textAppName = findViewById(R.id.textAppName);
            textAppName.setText(s);
        });

        findViewById(R.id.add_student_main).setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this , AddNewStudentActivity.class)); });

    }
}