package ru.bestschools.university.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bestschools.university.model.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Faculty findFacultyByColor(String color);

    Faculty findFacultyByNameIgnoreCaseOrColorIgnoreCase(String name, String color);

}
