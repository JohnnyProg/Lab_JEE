package gryta.jan.Lab8.repositories;

import gryta.jan.Lab8.domain.Student;
import gryta.jan.Lab8.dtos.StudentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select new gryta.jan.Lab8.dtos.StudentDto(s.name, s.surname, s.age, s.address.street, s.address.city, s.address.state, s.address.zip) from Student s")
    List<StudentDto> findAllNoAttachments();
}
