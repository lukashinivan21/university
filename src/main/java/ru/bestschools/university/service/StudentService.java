package ru.bestschools.university.service;

import ru.bestschools.university.model.Faculty;
import ru.bestschools.university.model.Student;

import java.util.List;

public interface StudentService {

    Student createStudent(Student student);

    Student readStudent(Long numberId);

    Student updateStudent(Student student);

    void deleteStudent(Long numberId);

    List<Student> findStudentsByAge(int age);

    List<Student> findStudentsByAgeBetween(int age1, int age2);

    List<Student> findStudentsByFacultyId(Long id);

    List<Student> findStudentsByFacultyName(String name);

    List<Student> findStudentsByFacultyColor(String color);

    Faculty getFacultyOfStudentWithId(Long id);
}
