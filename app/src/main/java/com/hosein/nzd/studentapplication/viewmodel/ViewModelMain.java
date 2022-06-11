package com.hosein.nzd.studentapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hosein.nzd.studentapplication.model.ApiService;
import com.hosein.nzd.studentapplication.model.ApiServiceProvider;
import com.hosein.nzd.studentapplication.model.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModelMain extends ViewModel {

    MutableLiveData<List<Student>> getStudent = new MutableLiveData<>();
    MutableLiveData<String> error = new MutableLiveData<>();

    public ViewModelMain() {
        ApiServiceProvider.getRetrofit().create(ApiService.class).getStudent().enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                List<Student> students = response.body();
                getStudent.setValue(students);
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                error.setValue(t.getMessage());
            }
        });
    }

    public LiveData<List<Student>> getGetStudent() {
        return getStudent;
    }

    public LiveData<String> getError() {
        return error;
    }
}
