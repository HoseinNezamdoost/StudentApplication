package com.hosein.nzd.studentapplication.model;

import androidx.lifecycle.LiveData;

import com.hosein.nzd.studentapplication.database.StudentDao;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
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

    public Completable refreshStudent() {
        return apiService.getStudent().doOnSuccess(students -> {studentDao.addStudent(students);}).ignoreElement();
    }

    public LiveData<List<Student>> getStudent() {
        return studentDao.getStudent();
    }
}
