package com.hosein.nzd.studentapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hosein.nzd.studentapplication.model.ApiService;
import com.hosein.nzd.studentapplication.model.ApiServiceProvider;
import com.hosein.nzd.studentapplication.model.Student;
import com.hosein.nzd.studentapplication.model.StudentRepository;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModelMain extends ViewModel {

    StudentRepository repository;
    MutableLiveData<String> error = new MutableLiveData<>();
    Disposable disposable;

    public ViewModelMain(StudentRepository repository) {
        this.repository = repository;
        repository.refreshStudent()
        .subscribeOn(Schedulers.io())
        .subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable = d;
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(@NonNull Throwable e) {
                error.postValue(e.getMessage());
            }
        });
    }

    public LiveData<List<Student>> getGetStudent() {
        return repository.getStudent();
    }

    public LiveData<String> getError() {
        return error;
    }

    @Override
    protected void onCleared() {
        disposable.dispose();
        super.onCleared();
    }
}
