package cohort_65.java.studentservice.service;

import cohort_65.java.studentservice.dto.NewStudentDto;
import cohort_65.java.studentservice.dto.ScoreDto;
import cohort_65.java.studentservice.dto.StudentDto;
import cohort_65.java.studentservice.dto.UpdateStudentDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface StudentService {

    boolean addStudent(NewStudentDto newStudent);

    StudentDto findStudent(int id);

    StudentDto removeStudent(int id);

    StudentDto updateStudent(UpdateStudentDto updateStudent, int id);

    boolean addScore(@PathVariable int id, @RequestBody ScoreDto scoreDto);
}
