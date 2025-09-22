package cohort_65.java.studentservice.service;

import cohort_65.java.studentservice.dto.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

public interface StudentService {

    boolean addStudent(NewStudentDto newStudent);

    StudentDto findStudent(int id);

    StudentDto removeStudent(int id);

    StudentDto updateStudent(UpdateStudentDto updateStudent, int id);

    boolean addScore(int id, ScoreDto scoreDto);

    List<StudentDto> findStudentsByName( String firstName, String LastName);

    Integer getStudentsNamesQuantity(List<FullNameStudentDto> names);

}
