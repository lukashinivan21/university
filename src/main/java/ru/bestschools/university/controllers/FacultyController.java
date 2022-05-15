package ru.bestschools.university.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.bestschools.university.model.Faculty;
import ru.bestschools.university.service.FacultyService;

@RestController
@RequestMapping("/faculties")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        if (faculty.getId() != 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return facultyService.createFaculty(faculty);
    }

    @GetMapping("{numberId}")
    public Faculty readFaculty(@PathVariable Long numberId) {
        Faculty foundFaculty = facultyService.readFaculty(numberId);
        if (foundFaculty == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return foundFaculty;
    }

    @PutMapping
    public Faculty updateFaculty(@RequestBody Faculty faculty) {
        Faculty updatedFaculty = facultyService.updateFaculty(faculty);
        if (updatedFaculty == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return updatedFaculty;
    }

    @DeleteMapping("{numberId}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable Long numberId) {
        facultyService.deleteFaculty(numberId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/facultyWithColor{color}")
    public Faculty findFacultyByColor(@PathVariable String color) {
        Faculty result = facultyService.findFacultyByColor(color);
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return result;
    }

    @GetMapping("/facultyWithNameOrColor")
    public ResponseEntity<Faculty> findFacultyWithNameOrColor(@RequestParam("name") String name,
                                                              @RequestParam("color") String color) {
        Faculty result = facultyService.findFacultyByNameOrColor(name, color);
        if (result == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
