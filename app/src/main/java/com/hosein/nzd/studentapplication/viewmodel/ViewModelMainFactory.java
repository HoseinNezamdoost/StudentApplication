package com.hosein.nzd.studentapplication.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.hosein.nzd.studentapplication.database.StudentDao;
import com.hosein.nzd.studentapplication.model.ApiService;
import com.hosein.nzd.studentapplication.model.StudentRepository;

public class ViewModelMainFactory implements ViewModelProvider.Factory {

    ApiService apiService;
    StudentDao studentDao;

    public ViewModelMainFactory(ApiService apiService, StudentDao studentDao) {
        this.apiService = apiService;
        this.studentDao = studentDao;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ViewModelMain(new StudentRepository(apiService , studentDao));
    }
}
