package com.quiz.quizapp.controller;

import com.quiz.quizapp.model.Question;
import com.quiz.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/addQuestion")
    public String addQuestionPage(Model model) {

        model.addAttribute("question", new Question());

        return "add-question";
    }

    @PostMapping("/saveQuestion")
    public String saveQuestion(@ModelAttribute Question question) {

        questionService.saveQuestion(question);

        return "redirect:/dashboard";
    }

    @GetMapping("/editQuestion/{id}")
    public String editQuestion(@PathVariable Long id, Model model) {

        Question question = questionService.getQuestionById(id);

        model.addAttribute("question", question);

        return "edit-question";
    }

    @PostMapping("/updateQuestion")
    public String updateQuestion(@ModelAttribute Question question) {

        questionService.updateQuestion(question);

        return "redirect:/dashboard";
    }

    @GetMapping("/deleteQuestion/{id}")
    public String deleteQuestion(@PathVariable Long id) {

        questionService.deleteQuestion(id);

        return "redirect:/dashboard";
    }
}
