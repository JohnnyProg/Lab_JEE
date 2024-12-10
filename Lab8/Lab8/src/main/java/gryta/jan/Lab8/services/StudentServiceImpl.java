package gryta.jan.Lab8.services;

import gryta.jan.Lab8.controllers.StudentController;
import gryta.jan.Lab8.converters.StudentConverter;
import gryta.jan.Lab8.converters.StudentMapper;
import gryta.jan.Lab8.domain.Student;
import gryta.jan.Lab8.dtos.StudentDto;
import gryta.jan.Lab8.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;
    private final StudentConverter studentConverter;
    private final StudentMapper studentMapper;
    @Override
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(studentMapper::mapStudentToStudentDto)
                .collect(Collectors.toList());
    }
}
