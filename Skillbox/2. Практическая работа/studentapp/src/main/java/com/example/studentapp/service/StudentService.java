package com.example.studentapp.service;

import com.example.studentapp.model.Student;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService {

    private final ApplicationEventPublisher eventPublisher;
    private final Map<Long, Student> students = new HashMap<>();
    private Long nextId = 1L;

    public StudentService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public Student addStudent(String firstName, String lastName, int age) {
        Student student = new Student(nextId++, firstName, lastName, age);
        students.put(student.getId(), student);
        eventPublisher.publishEvent(student); // Publish student created event
        return student;
    }

    public void removeStudent(Long id) {
        students.remove(id);
        eventPublisher.publishEvent(id); // Publish student removed event
    }

    public void clearStudents() {
        students.clear();
    }

    public Map<Long, Student> getAllStudents() {
        return students;
    }
}
