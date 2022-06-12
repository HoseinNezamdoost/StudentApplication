package com.hosein.nzd.studentapplication.database;

import android.content.Context;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.hosein.nzd.studentapplication.model.Student;

@Database(entities = {Student.class} , exportSchema = false , version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static AppDatabase appDatabase;
    public static AppDatabase getAppDatabase(Context context){
        if (appDatabase == null){
            appDatabase = Room.databaseBuilder(context , AppDatabase.class , "student_db").build();
        }
        return appDatabase;
    };

    public abstract StudentDao studentDao();

}
