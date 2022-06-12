package com.hosein.nzd.studentapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hosein.nzd.studentapplication.model.Student;
import com.hosein.nzd.studentapplication.model.StudentRepository;

import io.reactivex.rxjava3.core.Completable;

public class ViewModelAddStudent extends ViewModel {

    MutableLiveData<Boolean> progress = new MutableLiveData<>();
    StudentRepository repository;

    public ViewModelAddStudent(StudentRepository repository) {
        this.repository = repository;
        progress.setValue(false);
    }

    public Completable saveStudent(String firstName, String lastName, String course, String score) {
        return repository.saveStudent(firstName, lastName, course, score).doFinally(() -> {progress.postValue(true);});
    }

    public LiveData<Boolean> getProgress() {
        return progress;
    }
}
