package cohort_65.java.studentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    Integer id;
    String firstName;
    String lastName;
    Map<String,Integer> scores = new HashMap<>();
}
