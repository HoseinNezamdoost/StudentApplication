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
    MutableLiveData<String> toolbarText = new MutableLiveData<>();
    MutableLiveData<Boolean> progressbar = new MutableLiveData<>();
    Disposable disposable;

    public ViewModelMain(StudentRepository repository) {
        this.repository = repository;
        toolbarText.setValue("Receiving list of new students...");

        repository.refreshStudent()
                .subscribeOn(Schedulers.io())
                .doFinally(() -> {progressbar.postValue(false); toolbarText.postValue("StudentApplication");})
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

    public LiveData<List<Student>> getStudent() {
        return repository.getStudent();
    }

    public LiveData<String> getError() {
        return error;
    }

    public LiveData<String> getToolbarText() {
        return toolbarText;
    }

    public LiveData<Boolean> getProgressbar() {
        return progressbar;
    }

    @Override
    protected void onCleared() {
        disposable.dispose();
        super.onCleared();
    }
}
