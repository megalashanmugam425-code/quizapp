package com.quiz.quizapp.controller;

import com.quiz.quizapp.model.Admin;
import com.quiz.quizapp.model.Quiz;
import com.quiz.quizapp.service.AdminService;
import com.quiz.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private QuizService quizService;

    @GetMapping("/admin")
    public String adminLoginPage() {
        return "admin-login";
    }

    @PostMapping("/admin")
    public String adminLogin(@RequestParam String username,
                             @RequestParam String password,
                             Model model) {

        Admin admin = adminService.login(username, password);

        if (admin != null) {
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Invalid Username or Password");
            return "admin-login";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        List<Quiz> quizzes = quizService.getAllQuizzes();
        model.addAttribute("quizzes", quizzes);

        return "dashboard";
    }
}
