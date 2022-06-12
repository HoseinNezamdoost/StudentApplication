package com.hosein.nzd.studentapplication.model;

import androidx.lifecycle.LiveData;

import com.google.gson.JsonObject;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @GET("getStudent.php")
    Single<List<Student>> getStudent();

    @POST("putStudent.php")
    Single<Student> postStudent(@Body JsonObject jsonObject);

}
