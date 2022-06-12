package com.hosein.nzd.studentapplication.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.hosein.nzd.studentapplication.model.Student;

import java.util.List;

@Dao
public interface StudentDao {

    @Query("SELECT * FROM student_table")
    LiveData<List<Student>> getStudent();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addStudents(List<Student> students);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addStudent(Student student);

}
