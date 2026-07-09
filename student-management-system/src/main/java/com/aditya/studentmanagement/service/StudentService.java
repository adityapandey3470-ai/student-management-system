package com.aditya.studentmanagement.service;

import java.util.List;

import com.aditya.studentmanagement.dto.StudentRequestDto;
import com.aditya.studentmanagement.dto.StudentResponseDto;
import com.aditya.studentmanagement.entity.Student;

public interface StudentService {

    StudentResponseDto saveStudent(StudentRequestDto studentRequestDto);
    List<StudentResponseDto> getAllStudents();
    StudentResponseDto getStudentById(Long id);
    StudentResponseDto updateStudent(Long id, StudentRequestDto newStudentRequestDto);
    void deleteStudent(Long id);
    void permanentDeleteStudent(Long id);
}
