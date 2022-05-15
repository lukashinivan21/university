package ru.bestschools.university.service;

import org.springframework.stereotype.Service;
import ru.bestschools.university.model.Faculty;
import ru.bestschools.university.model.Student;
import ru.bestschools.university.repositories.StudentRepository;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student readStudent(Long numberId) {
        return studentRepository.findById(numberId).orElse(null);
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long numberId) {
        studentRepository.deleteById(numberId);
    }

    public List<Student> findStudentsByAge(int age) {
        List<Student> result = studentRepository.findStudentsByAge(age);
        return checkListOnNull(result);
    }

    public List<Student> findStudentsByAgeBetween(int age1, int age2) {
        List<Student> result = studentRepository.findStudentsByAgeBetween(age1, age2);
        return checkListOnNull(result);
    }

    public List<Student> findStudentsByFacultyId(Long id) {
        List<Student> result = studentRepository.findStudentsByFaculty_Id(id);
        return checkListOnNull(result);
    }

    public List<Student> findStudentsByFacultyName(String name) {
        List<Student> result = studentRepository.findStudentsByFaculty_NameIgnoreCase(name);
        return checkListOnNull(result);
    }

    public List<Student> findStudentsByFacultyColor(String color) {
        List<Student> result = studentRepository.findStudentsByFaculty_ColorIgnoreCase(color);
        return checkListOnNull(result);
    }

    @Override
    public Faculty getFacultyOfStudentWithId(Long id) {
        Student result = studentRepository.findById(id).orElse(null);
        if (result == null) {
            return null;
        }
        return result.getFaculty();
    }

    private List<Student> checkListOnNull(List<Student> checkedList) {
        if (checkedList.isEmpty()) {
            return null;
        }
        return checkedList;
    }
}
