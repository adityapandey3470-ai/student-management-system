package com.aditya.studentmanagement.mapper;

import com.aditya.studentmanagement.dto.StudentRequestDto;
import com.aditya.studentmanagement.dto.StudentResponseDto;
import com.aditya.studentmanagement.entity.Student;
import org.springframework.stereotype.Component;


@Component
public class StudentMapper {

    public Student toEntity(StudentRequestDto studentRequestDto) {
        Student student = new Student();
        student.setFirstName(studentRequestDto.getFirstName());
        student.setLastName(studentRequestDto.getLastName());
        student.setEmail(studentRequestDto.getEmail());
        student.setPhoneNumber(studentRequestDto.getPhoneNumber());
        student.setCourse(studentRequestDto.getCourse());
        student.setSemester(studentRequestDto.getSemester());
        return student;
    }


    public StudentResponseDto toResponseDto( Student student){
        StudentResponseDto responseDto = new StudentResponseDto();
        responseDto.setId(student.getId());
        responseDto.setRollNo(student.getRollNo());
        responseDto.setFirstName(student.getFirstName());
        responseDto.setLastName(student.getLastName());
        responseDto.setEmail(student.getEmail());
        responseDto.setPhoneNumber(student.getPhoneNumber());
        responseDto.setCourse(student.getCourse());
        responseDto.setSemester(student.getSemester());
        return responseDto;
    }



}
