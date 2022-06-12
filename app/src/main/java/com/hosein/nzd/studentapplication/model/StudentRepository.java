package com.hosein.nzd.studentapplication.model;

import androidx.lifecycle.LiveData;

import com.google.gson.JsonObject;
import com.hosein.nzd.studentapplication.database.StudentDao;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

public class StudentRepository {

    private ApiService apiService;
    private StudentDao studentDao;

    public StudentRepository(ApiService apiService, StudentDao studentDao) {
        this.apiService = apiService;
        this.studentDao = studentDao;
    }

    public Completable refreshStudent() {
        return apiService.getStudent().doOnSuccess(students -> {studentDao.addStudents(students);}).ignoreElement();
    }

    public LiveData<List<Student>> getStudent() {
        return studentDao.getStudent();
    }

    public Completable saveStudent(String firstName , String lastName , String course , String score){

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("firstName", firstName);
        jsonObject.addProperty("lastName" , lastName);
        jsonObject.addProperty("course" , course);
        jsonObject.addProperty("score" , score);
        
        return apiService.postStudent(jsonObject).doOnSuccess(student -> {studentDao.addStudent(student);}).ignoreElement();
    }

}
