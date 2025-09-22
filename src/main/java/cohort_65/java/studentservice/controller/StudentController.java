package cohort_65.java.studentservice.controller;

import cohort_65.java.studentservice.dto.*;
import cohort_65.java.studentservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/student")
    public boolean addStudent(@RequestBody NewStudentDto newStudent) {
        return studentService.addStudent(newStudent);
    }

    @GetMapping("/student/{id}")
    public StudentDto findStudent(@PathVariable int id) {
        return studentService.findStudent(id);
    }

    @DeleteMapping("/student/{id}")
    public StudentDto removeStudent(@PathVariable int id) {
        return studentService.removeStudent(id);
    }

    @PutMapping("/student/{id}")
    public StudentDto updateStudent(@RequestBody UpdateStudentDto updateStudent,
                                    @PathVariable int id) {
        return studentService.updateStudent(updateStudent, id);
    }


    @PutMapping("/score/student/{id}")
    public Boolean addScore(@PathVariable int id, @RequestBody ScoreDto scoreDto) {
        return studentService.addScore(id, scoreDto);
    }

    //todo запит на цей метод дає помилку 400
    @GetMapping("/students/fullname")
    public List<StudentDto> findStudentsByName(@RequestParam  String firstName,
                                               @RequestParam  String lastName) {
        return studentService.findStudentsByName(firstName,lastName);
    }


    @PostMapping("/quantity/students")
    public Integer getStudentsNamesQuantity(@RequestBody List<FullNameStudentDto> names) {
        return studentService.getStudentsNamesQuantity(names);
    }
    /*
    @GetMapping("/students/exam/{exam}/minscore/{minScore}")
    public List<StudentDto> getStudentsByExamMinScore(@PathVariable String exam, @PathVariable Integer minScore) {
        return studentService.getStudentsByExamMinScore(exam, minScore);
    }
    */
}
