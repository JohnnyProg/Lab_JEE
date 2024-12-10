package gryta.jan.Lab8.services;

import gryta.jan.Lab8.domain.Student;
import gryta.jan.Lab8.dtos.StudentDto;

import java.util.List;

public interface StudentService {
    List<StudentDto> getAllStudents();
}
