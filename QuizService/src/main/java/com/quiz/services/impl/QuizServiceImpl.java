package com.quiz.services.impl;

import com.quiz.entities.Question;
import com.quiz.entities.Quiz;
import com.quiz.repositories.QuizRepository;
import com.quiz.services.QuestionClient;
import com.quiz.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionClient questionClient;

    public QuizServiceImpl(QuizRepository quizRepository, QuestionClient questionClient) {
        this.quizRepository = quizRepository;
        this.questionClient = questionClient;
    }

    //    public QuizServiceImpl(QuizRepository quizRepository) {
//        this.quizRepository = quizRepository;
//    }

    @Override
    public Quiz add(Quiz quiz) {
        return quizRepository.save(quiz);
    }

//    @Override
//    public List<Quiz> get() {
//        return quizRepository.findAll();
//    }

    @Override
    public List<Quiz> get() {
        List<Quiz> quizzes = quizRepository.findAll();

        List<Quiz> newQuizList = quizzes.stream().map(quiz -> {
            quiz.setQuestions(questionClient.getQuestionByQuizId(quiz.getId()));
            return quiz;
        }).collect(Collectors.toList());
        return  newQuizList;
    }

//        List<Quiz> newQuizList = new ArrayList<>();
//        for (Quiz quiz : quizList) {
//            quiz.setQuestions(questionClient.getQuestionByQuizId(quiz.getId()));

        //My Implementation

//            List<Question> questions = questionClient.getQuestionByQuizId(quiz.getId());
//            for (Question question : questions) {
//                System.out.println("Question ID: " + question.getId());
//            }
//            quiz.setQuestions(questions);

        //My Implementation

        //Quiz apply = quiz;
        //newQuizList.add(apply);
    //}
        //return newQuizList;

//    @Override
//    public Quiz get(Long id) {
//        return quizRepository.findById(id).orElseThrow(()->new RuntimeException("Quiz not found"));
//    }

    @Override
    public Quiz get(Long id){
        Quiz quiz = quizRepository.findById(id).orElseThrow(()->new RuntimeException("Quiz not found"));
        //quiz.setId(questionClient.getQuestionByQuizId());
        quiz.setQuestions(questionClient.getQuestionByQuizId(quiz.getId()));
        return quiz;
    }
}