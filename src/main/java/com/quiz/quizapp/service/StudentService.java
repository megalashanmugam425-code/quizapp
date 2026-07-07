package com.quiz.quizapp.service;

import com.quiz.quizapp.model.Student;
import com.quiz.quizapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    public Student login(String email, String password) {
        return studentRepository
                .findByEmailAndPassword(email, password)
                .orElse(null);
    }
}
