package com.example.studentapp;

import com.example.studentapp.service.StudentService;
import com.example.studentapp.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public Collection<Student> getAllStudents() {
        return studentService.getAllStudents().values();
    }
}
