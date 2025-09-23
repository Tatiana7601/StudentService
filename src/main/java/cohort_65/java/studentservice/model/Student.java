package cohort_65.java.studentservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private Integer id;
    @Setter
    private String firstName;
    @Setter
    private String lastName;
    Map<String, Integer> scores = new HashMap<>();

    public Student(Integer id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public boolean addScore(String subject, Integer score) {
        return scores.put(subject, score) == null;
    }
}
