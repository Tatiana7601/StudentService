package cohort_65.java.studentservice.service;

import cohort_65.java.studentservice.dao.StudentRepository;
import cohort_65.java.studentservice.dto.*;
import cohort_65.java.studentservice.dto.exception.StudentNotFoundException;
import cohort_65.java.studentservice.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Override
    public boolean addStudent(NewStudentDto newStudent) {
        if (studentRepository.findById(newStudent.getId()).isPresent()) {
            return false;
        }
        Student student = new Student(
                newStudent.getId(),
                newStudent.getFirstName(),
                newStudent.getLastName(),
                new HashMap<>());

        studentRepository.save(student);
        return true;
    }

    @Override
    public StudentDto findStudent(int id) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        return new StudentDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getScores());
    }

    @Override
    public StudentDto removeStudent(int id) {
        Student student = studentRepository.findById(id).
                orElseThrow(StudentNotFoundException::new);
        studentRepository.deleteById(id);
        return new StudentDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getScores());
    }

    @Override
    public StudentDto updateStudent(UpdateStudentDto updateStudent, int id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(StudentNotFoundException::new);

        student.setFirstName(updateStudent.getFirstName());
        student.setLastName(updateStudent.getLastName());
        studentRepository.save(student);


        return new StudentDto(
                student.getId(), student.getFirstName(),
                student.getLastName(),
                student.getScores());
    }

    @Override
    public boolean addScore(int id, ScoreDto scoreDto) {
        Student student = studentRepository.findById(id).
                orElseThrow(StudentNotFoundException::new);
        student.getScores().put(scoreDto.getExam(),  scoreDto.getScore());
        studentRepository.save(student);
        return true;
    }

    @Override
    public List<StudentDto> findStudentsByName(String firstName, String lastName) {
        return studentRepository.findAll().stream()
                .filter(s -> s.getFirstName().equals(firstName) && s.getLastName().equals(lastName))
                .map(s -> new StudentDto(s.getId(), s.getFirstName(), s.getLastName(), s.getScores()))
                .collect(Collectors.toList());
    }

    @Override
    public Integer getStudentsNamesQuantity(List<FullNameStudentDto> names) {
        if (names == null || names.isEmpty()) {
            return 0;
        }

        return (int) studentRepository.findAll().stream()
                .filter(student -> names.stream()
                        .anyMatch(name -> student.getFirstName().equals(name.getFirstName())
                                && student.getLastName().equals(name.getLastName())))
                .count();
    }

    @Override
    public List<StudentDto> getStudentsByExamMinScore(String exam, Integer minScore) {
        return studentRepository.findAll().stream()
                .filter(student -> {
                    Integer score = student.getScores().get(exam);
                    return score != null && score >= minScore;
                })
                .map(student -> new StudentDto(
                        student.getId(),
                        student.getFirstName(),
                        student.getLastName(),
                        student.getScores()
                ))
                .collect(Collectors.toList());
    }
}
