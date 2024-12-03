package com.gryta.pai_lab7.repositories;

import com.gryta.pai_lab7.entities.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "users-repo")
public interface StudentRepository extends CrudRepository<Student, Long> {

}
