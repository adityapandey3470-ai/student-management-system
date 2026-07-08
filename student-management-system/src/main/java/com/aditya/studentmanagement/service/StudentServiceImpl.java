package com.aditya.studentmanagement.service;

import com.aditya.studentmanagement.entity.Student;
import com.aditya.studentmanagement.exception.StudentNotFoundException;
import com.aditya.studentmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;



import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);

    }

    @Override
    public List<Student> getAllStudents() {
       return studentRepository.findByDeletedFalse();

    }

    @Override
    public Student getStudentById(Long id) {
        Student student = studentRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
        return student;
    }

    @Override
    public Student updateStudent(Long id, Student newStudent) {
        Student student = studentRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));

        student.setRollNo(newStudent.getRollNo());
        student.setFirstName(newStudent.getFirstName());
        student.setLastName(newStudent.getLastName());
        student.setEmail(newStudent.getEmail());
        student.setPhoneNumber(newStudent.getPhoneNumber());
        student.setCourse(newStudent.getCourse());
        student.setSemester(newStudent.getSemester());
        return studentRepository.save(student);
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
