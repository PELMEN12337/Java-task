package com.example.studentapp.init;

import com.example.studentapp.service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StudentInitializer implements CommandLineRunner {

    private final StudentService studentService;

    public StudentInitializer(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void run(String... args) throws Exception {
        // При желании можно оставить фиксированных студентов для инициализации:
        studentService.addStudent("John", "Doe", 20);
        studentService.addStudent("Jane", "Smith", 22);
        System.out.println("Students initialized.");
    }
}
