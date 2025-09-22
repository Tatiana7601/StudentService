package cohort_65.java.studentservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class UpdateStudentDto {
    Integer id;
    String firstName;
    String lastName;
    Map<String,Integer> scores = new HashMap<>();
}
