package ru.bestschools.university.service;

import org.springframework.stereotype.Service;
import ru.bestschools.university.model.Faculty;
import ru.bestschools.university.repositories.FacultyRepository;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty readFaculty(Long numberId) {
        return facultyRepository.findById(numberId).orElse(null);
    }

    public Faculty updateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long numberId) {
        facultyRepository.deleteById(numberId);
    }

    public Faculty findFacultyByColor(String color) {
        Faculty result = facultyRepository.findFacultyByColor(color);
        return checkFacultyOnNull(result);
    }

    public Faculty findFacultyByNameOrColor(String name, String color) {
        Faculty result = facultyRepository.findFacultyByNameIgnoreCaseOrColorIgnoreCase(name, color);
        return checkFacultyOnNull(result);
    }

    private Faculty checkFacultyOnNull(Faculty checkedFaculty) {
        if (checkedFaculty == null) {
            return null;
        }
        return checkedFaculty;
    }
}
