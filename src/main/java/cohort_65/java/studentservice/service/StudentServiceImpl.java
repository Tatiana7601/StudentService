package cohort_65.java.studentservice.service;

import cohort_65.java.studentservice.dao.StudentRepository;
import cohort_65.java.studentservice.dto.*;
import cohort_65.java.studentservice.dto.exception.StudentNotFoundException;
import cohort_65.java.studentservice.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
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
                newStudent.getLastName()
                );

        studentRepository.save(student);
        return true;
    }

    @Override
    public StudentDto findStudent(int id) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        return new StudentDto(student.getId(), student.getFirstName(), student.getLastName());
    }

    @Override
    public StudentDto removeStudent(int id) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        studentRepository.deleteById(id);
        return new StudentDto(student.getId(), student.getFirstName(), student.getLastName());
    }

    @Override
    public StudentDto updateStudent(UpdateStudentDto updateStudent, int id) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        if (updateStudent.getFirstName() != null) {
            student.setFirstName(updateStudent.getFirstName());
        }
        if (updateStudent.getLastName() != null) {
            student.setLastName(updateStudent.getLastName());
        }
        studentRepository.save(student);
        return new StudentDto(student.getId(), student.getFirstName(), student.getLastName());
    }


    public Boolean addScore(int id, ScoreDto scoreDto) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        boolean res = student.addScore(scoreDto.getExamName(), scoreDto.getScore());
        studentRepository.save(student);
        return res;
    }

    @Override
    public List<StudentDto> findStudentsByName(String name) {
        return studentRepository.  findByFirstNameIgnoreCase(name)
                .map(student -> new StudentDto(student.getId(),
                        student.getFirstName(), student.getLastName()))
                .toList();
    }


    @Override
    public Integer getStudentsNamesQuantity(Set<String> names) {
        return studentRepository.findByFirstNameIn(names).size();
    }

    @Override
    public List<StudentDto> getStudentsByExamMinScore(String exam, Integer minScore) {
        return studentRepository.findByExamMinScore(exam, minScore)
                .stream()
                .map(student -> new StudentDto(student.getId(),
                        student.getFirstName(), student.getLastName()))
                .toList();
    }
}
