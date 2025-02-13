package com.question.controllers;

import com.question.entities.Question;
import com.question.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public Question create(@RequestBody Question question){
        return questionService.add(question);
    }

    @GetMapping
    public List<Question> getAll(){
        return questionService.get();
    }

    @GetMapping("/{questionId}")
    public Question getOne(@PathVariable Long questionId){
        return questionService.get(questionId);
    }

    @GetMapping("quiz/{quizId}")
    public List<Question> getByQuizId(@PathVariable Long quizId){
        return questionService.getQuestionByQuizId(quizId);
    }
}