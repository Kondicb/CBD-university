package com.fakultet.usersservice.service;

import com.fakultet.usersservice.exception.ResourceNotFoundException;
import com.fakultet.usersservice.model.Student;
import com.fakultet.usersservice.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student getById(Integer id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student sa id=" + id + " ne postoji"));
    }

    public List<Student> getBySmer(Integer smerId) {
        return studentRepository.findBySmerId(smerId);
    }
}
