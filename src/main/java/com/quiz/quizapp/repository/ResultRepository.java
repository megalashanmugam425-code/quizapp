package com.quiz.quizapp.repository;

import com.quiz.quizapp.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Long> {
}

