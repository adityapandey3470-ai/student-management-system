package com.aditya.studentmanagement.service;

import com.aditya.studentmanagement.dto.StudentRequestDto;
import com.aditya.studentmanagement.dto.StudentResponseDto;
import com.aditya.studentmanagement.entity.Student;
import com.aditya.studentmanagement.exception.StudentNotFoundException;
import com.aditya.studentmanagement.mapper.StudentMapper;
import com.aditya.studentmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;



import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {



    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;


    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }


    @Override
    public StudentResponseDto saveStudent(StudentRequestDto studentRequestDto) {
        Student student = studentMapper.toEntity(studentRequestDto);

        Student lastStudent = studentRepository.findTopByOrderByRollNoDesc();
        if (lastStudent != null) {
            student.setRollNo(lastStudent.getRollNo() + 1);
        } else {
            student.setRollNo(1001L); // Start from 1001 if no students exist
        }
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toResponseDto(savedStudent);
    }

    @Override
    public List<StudentResponseDto> getAllStudents() {
        List<Student> students = studentRepository.findByDeletedFalse();
        return students.stream()
                .map(studentMapper::toResponseDto)
                .toList();
    }

    @Override
    public StudentResponseDto getStudentById(Long id) {
        Student student = studentRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
        return studentMapper.toResponseDto(student);
    }

    @Override
    public StudentResponseDto updateStudent(Long id, StudentRequestDto newStudentRequestDto) {
        Student student = studentRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));

         studentMapper.updateStudentFromDto(newStudentRequestDto, student);


        Student updatedStudent = studentRepository.save(student);
        return studentMapper.toResponseDto(updatedStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = studentRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
        student.setDeleted(true);
        studentRepository.save(student);


    }

    @Override
    public void permanentDeleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }

}
