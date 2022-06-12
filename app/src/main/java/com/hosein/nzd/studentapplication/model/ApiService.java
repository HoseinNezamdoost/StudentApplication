package com.hosein.nzd.studentapplication.model;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("getStudent.php")
    Single<List<Student>> getStudent();
}
