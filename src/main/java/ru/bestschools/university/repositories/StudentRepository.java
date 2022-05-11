package ru.bestschools.university.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bestschools.university.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
