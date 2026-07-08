package com.aditya.studentmanagement.service;

import java.util.List;
import com.aditya.studentmanagement.entity.Student;

public interface StudentService {

    Student saveStudent(Student student);
    List<Student> getAllStudents();
    Student getStudentById(Long id);
    Student updateStudent(Long id, Student newStudent);
    void deleteStudent(Long id);
    void permanentDeleteStudent(Long id);
}
