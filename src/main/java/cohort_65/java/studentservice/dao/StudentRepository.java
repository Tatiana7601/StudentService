package cohort_65.java.studentservice.dao;

import cohort_65.java.studentservice.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.stream.Stream;


public interface StudentRepository extends MongoRepository<Student,Integer> {
        Stream<Student> findByFirstNameIgnoreCase(String Name);

}

