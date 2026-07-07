package com.quiz.quizapp.repository;

import com.quiz.quizapp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByEmail(String email);

    Optional<Student> findByEmailAndPassword(String email, String password);

}
