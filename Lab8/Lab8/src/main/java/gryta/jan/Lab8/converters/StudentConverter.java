package gryta.jan.Lab8.converters;

import gryta.jan.Lab8.domain.Student;
import gryta.jan.Lab8.dtos.StudentDto;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@Component
public class StudentConverter implements Converter<Student, StudentDto> {


    @Override
    public StudentDto convert(Student source) {
        return StudentDto
                .builder()
                .name(source.getName())
                .surname(source.getSurname())
                .age(source.getAge())
                .street(source.getAddress().getStreet())
                .city(source.getAddress().getCity())
                .zip(source.getAddress().getZip())
                .state(source.getAddress().getState())
                .build();
    }
}
