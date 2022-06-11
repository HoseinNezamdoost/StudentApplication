package com.hosein.nzd.studentapplication.model;

import androidx.lifecycle.LiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("getStudent.php")
    Call<List<Student>> getStudent();
}
