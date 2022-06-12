package com.hosein.nzd.studentapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.hosein.nzd.studentapplication.R;
import com.hosein.nzd.studentapplication.database.AppDatabase;
import com.hosein.nzd.studentapplication.model.ApiServiceProvider;
import com.hosein.nzd.studentapplication.model.StudentRepository;
import com.hosein.nzd.studentapplication.viewmodel.ViewModelAddStudent;
import com.hosein.nzd.studentapplication.viewmodel.ViewModelFactory;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AddNewStudentActivity extends AppCompatActivity {

    ExtendedFloatingActionButton saveStudentFab;
    ProgressBar progressBar;
    TextInputEditText firstName, lastName, course, score;
    private static final String TAG = "AddNewStudentActivity";
    Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_student);

        firstName = findViewById(R.id.firstName_edt);
        lastName = findViewById(R.id.lastName_edt);
        course = findViewById(R.id.course_edt);
        score = findViewById(R.id.score_edt);
        progressBar = findViewById(R.id.progressAddStudent);
        saveStudentFab = findViewById(R.id.saveStudentFab);

        ViewModelAddStudent viewModelAddStudent = new ViewModelProvider(this, new ViewModelFactory(new StudentRepository(ApiServiceProvider.getApiService()
                , AppDatabase.getAppDatabase(getApplicationContext()).studentDao()))).get(ViewModelAddStudent.class);

        saveStudentFab.setOnClickListener(view -> {

            viewModelAddStudent.getProgress().observe(this, aBoolean -> {
                progressBar.setVisibility(aBoolean ? View.VISIBLE : View.GONE);
            });

            viewModelAddStudent.saveStudent(firstName.getText().toString()
                    , lastName.getText().toString(), course.getText().toString(),
                    score.getText().toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new CompletableObserver() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                            disposable = d;
                        }

                        @Override
                        public void onComplete() {
                            finish();

                        }

                        @Override
                        public void onError(@NonNull Throwable e) {

                        }
                    });
        });

    }
}