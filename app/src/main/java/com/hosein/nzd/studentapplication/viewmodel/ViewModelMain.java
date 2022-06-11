package com.hosein.nzd.studentapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hosein.nzd.studentapplication.model.ApiService;
import com.hosein.nzd.studentapplication.model.ApiServiceProvider;
import com.hosein.nzd.studentapplication.model.Student;
import com.hosein.nzd.studentapplication.model.StudentRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModelMain extends ViewModel {

    StudentRepository repository;
    MutableLiveData<String> error = new MutableLiveData<>();

    public ViewModelMain(StudentRepository repository) {
        this.repository = repository;
        repository.refreshStudent();
    }

    public LiveData<List<Student>> getGetStudent() {
        return repository.getStudent();
    }

    public LiveData<String> getError() {
        return error;
    }
}
