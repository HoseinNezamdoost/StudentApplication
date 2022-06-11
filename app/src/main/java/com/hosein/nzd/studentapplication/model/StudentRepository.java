package com.hosein.nzd.studentapplication.model;

import androidx.lifecycle.LiveData;

import com.hosein.nzd.studentapplication.database.StudentDao;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentRepository {

    private ApiService apiService;
    private StudentDao studentDao;

    public StudentRepository(ApiService apiService, StudentDao studentDao) {
        this.apiService = apiService;
        this.studentDao = studentDao;
    }

    public void refreshStudent() {
        apiService.getStudent().enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                List<Student> students = response.body();
                studentDao.addStudent(students);
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {

            }
        });
    }

    public LiveData<List<Student>> getStudent() {
        return studentDao.getStudent();
    }
}
