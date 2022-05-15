package ru.bestschools.university.service;

import ru.bestschools.university.model.Faculty;

public interface FacultyService {

    Faculty createFaculty(Faculty faculty);

    Faculty readFaculty(Long numberId);

    Faculty updateFaculty(Faculty faculty);

    void deleteFaculty(Long numberId);

    Faculty findFacultyByColor(String color);

    Faculty findFacultyByNameOrColor(String name, String color);
}
