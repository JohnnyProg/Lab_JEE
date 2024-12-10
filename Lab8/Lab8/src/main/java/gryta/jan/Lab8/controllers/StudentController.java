package gryta.jan.Lab8.controllers;

import gryta.jan.Lab8.domain.Student;
import gryta.jan.Lab8.dtos.StudentDto;
import gryta.jan.Lab8.services.StudentService;
import gryta.jan.Lab8.services.StudentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/students")
public class StudentController {
    private final StudentServiceImpl studentService;

    @GetMapping
    public List<StudentDto> getAllStudents() {
        return studentService.getAllStudents();
    }

}
