package com.quiz.quizapp.controller;

import com.quiz.quizapp.model.Question;
import com.quiz.quizapp.model.Quiz;
import com.quiz.quizapp.model.Result;
import com.quiz.quizapp.model.Student;
import com.quiz.quizapp.service.QuestionService;
import com.quiz.quizapp.service.QuizService;
import com.quiz.quizapp.service.ResultService;
import com.quiz.quizapp.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ResultService resultService;

    @GetMapping("/student/register")
    public String registerPage(Model model) {
        model.addAttribute("student", new Student());
        return "student-register";
    }

    @PostMapping("/student/register")
    public String registerStudent(@ModelAttribute Student student) {
        studentService.saveStudent(student);
        return "redirect:/student/login";
    }

    @GetMapping("/student/login")
    public String loginPage() {
        return "student-login";
    }

    @PostMapping("/student/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        Model model) {

        Student student = studentService.login(email, password);

        if (student != null) {
            return "redirect:/student/dashboard";
        } else {
            model.addAttribute("error", "Invalid Email or Password");
            return "student-login";
        }
    }

    @GetMapping("/student/dashboard")
    public String dashboard(Model model) {

        List<Quiz> quizzes = quizService.getAllQuizzes();

        model.addAttribute("quizzes", quizzes);

        return "student-dashboard";
    }

    @GetMapping("/quiz/{id}")
    public String startQuiz(@PathVariable Long id, Model model) {

        List<Question> questions = questionService.getAllQuestions();

        model.addAttribute("questions", questions);

        return "take-quiz";
    }

    @PostMapping("/submitQuiz")
    public String submitQuiz(@RequestParam Map<String, String> answers,
                             Model model) {

        int score = 0;

        for (String key : answers.keySet()) {

            if (key.startsWith("q")) {

                Long questionId = Long.parseLong(key.substring(1));

                Question question = questionService.getQuestionById(questionId);

                if (question != null &&
                        question.getAnswer().equals(answers.get(key))) {

                    score++;
                }
            }
        }

        Result result = new Result();
        result.setStudentEmail("student@test.com");
        result.setScore(score);

        resultService.saveResult(result);

        model.addAttribute("score", score);

        return "result";
    }
}
