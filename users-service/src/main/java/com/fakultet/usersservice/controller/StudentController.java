package com.fakultet.usersservice.controller;

import com.fakultet.usersservice.model.Student;
import com.fakultet.usersservice.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/studenti")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAll() {
        return studentService.getAll();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Integer id) {
        return studentService.getById(id);
    }

    @GetMapping("/smer/{smerId}")
    public List<Student> getBySmer(@PathVariable Integer smerId) {
        return studentService.getBySmer(smerId);
    }
}
