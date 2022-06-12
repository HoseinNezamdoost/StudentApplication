package com.hosein.nzd.studentapplication.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.hosein.nzd.studentapplication.database.StudentDao;
import com.hosein.nzd.studentapplication.model.ApiService;
import com.hosein.nzd.studentapplication.model.StudentRepository;
import com.hosein.nzd.studentapplication.view.MainActivity;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private StudentRepository repository;

    public ViewModelFactory(StudentRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(ViewModelMain.class))
            return (T) new ViewModelMain(repository);
        else if (modelClass.isAssignableFrom(ViewModelAddStudent.class))
            return (T) new ViewModelAddStudent(repository);
        else
            throw new IllegalArgumentException("error to viewModel factory");
    }
}
