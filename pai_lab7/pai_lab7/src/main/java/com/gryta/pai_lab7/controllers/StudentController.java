package com.gryta.pai_lab7.controllers;

import com.gryta.pai_lab7.entities.Student;
import com.gryta.pai_lab7.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAll() {
        return studentService.getStudentList();
    }

    @PostMapping
    public Student add(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @RequestBody Student student) {
        return studentService.updateStudent(id, student).orElseThrow(() ->
                new RuntimeException("Student not found with id: " + id));
    }
}
