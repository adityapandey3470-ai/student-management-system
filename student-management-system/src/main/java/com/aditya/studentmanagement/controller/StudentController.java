package com.aditya.studentmanagement.controller;


import com.aditya.studentmanagement.dto.StudentRequestDto;
import com.aditya.studentmanagement.dto.StudentResponseDto;
import com.aditya.studentmanagement.entity.Student;
import com.aditya.studentmanagement.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping
    public StudentResponseDto createStudent(@Valid @RequestBody StudentRequestDto studentRequestDto){
        return  studentService.saveStudent(studentRequestDto);
    }

    @GetMapping
    public List<StudentResponseDto> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentResponseDto getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

    @PutMapping("/{id}")
    public StudentResponseDto updateStudent(@PathVariable Long id, @Valid @RequestBody StudentRequestDto studentRequestDto){
        return studentService.updateStudent(id, studentRequestDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/permanent/{id}")
    public ResponseEntity<Void> permanentDeleteStudent(@PathVariable Long id){
        studentService.permanentDeleteStudent(id);
        return ResponseEntity.noContent().build();
    }

}
