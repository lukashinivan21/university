package ru.bestschools.university.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.bestschools.university.model.Faculty;
import ru.bestschools.university.model.Student;
import ru.bestschools.university.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        if (student.getId() != 0 || student.getFaculty().getId() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return studentService.createStudent(student);
    }

    @GetMapping("{numberId}")
    public Student readStudent(@PathVariable Long numberId) {
        Student foundStudent = studentService.readStudent(numberId);
        if (foundStudent == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return foundStudent;
    }

    @PutMapping
    public Student updateStudent(@RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(student);
        if (updatedStudent == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return updatedStudent;
    }

    @DeleteMapping("{numberId}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long numberId) {
        studentService.deleteStudent(numberId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/studentsWithAge{age}")
    public ResponseEntity<List<Student>> studentsWithAge(@PathVariable int age) {
        List<Student> result = studentService.findStudentsByAge(age);
        if (result == null) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/studentsWithAgeBetween")
    public List<Student> studentsWithAgeBetween(@RequestParam("age1") int age1, @RequestParam("age2") int age2) {
        List<Student> result = studentService.findStudentsByAgeBetween(age1, age2);
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return result;
    }

    @GetMapping("/studentsOfFaculty")
    public List<Student> studentsOfFaculty(@RequestParam(required = false) Long id,
                                           @RequestParam(required = false) String name,
                                           @RequestParam(required = false) String color) {
        List<Student> result = null;
        if (id != null) {
            result = studentService.findStudentsByFacultyId(id);
        }
        if (name != null) {
            result = studentService.findStudentsByFacultyName(name);
        }
        if (color != null) {
            result = studentService.findStudentsByFacultyColor(color);
        }
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return result;
    }

    @GetMapping("/facultyOfStudentWithId{numberId}")
    public Faculty facultyOfStudent(@PathVariable Long numberId) {
        Faculty result = studentService.getFacultyOfStudentWithId(numberId);
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return result;
    }
}
