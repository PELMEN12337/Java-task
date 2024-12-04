package com.example.studentapp.listener;

import com.example.studentapp.model.Student;
import com.example.studentapp.service.StudentService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StudentEventListener {

    @EventListener
    public void onStudentCreated(Student student) {
        System.out.println("Студент создан: " + student);
    }

    @EventListener
    public void onStudentRemoved(Long studentId) {
        System.out.println("Студент удален с ID: " + studentId);
    }
}
