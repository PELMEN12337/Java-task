package com.example.studentapp.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class StudentCommands {

    @ShellMethod("List all students")
    public String list() {
        return "List of students: John Doe, Jane Smith";
    }

    @ShellMethod("Add a student")
    public String add(String firstName, String lastName, int age) {
        return "Student added: " + firstName + " " + lastName + ", age " + age;
    }

    @ShellMethod("Clear all students")
    public String clear() {
        return "All students cleared!";
    }
}
