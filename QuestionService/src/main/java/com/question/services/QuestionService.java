package com.question.services;

import com.question.entities.Question;

import java.util.List;

public interface QuestionService {
    Question add(Question question);
    List<Question> get();
    Question get(Long questionId);
    List<Question> getQuestionByQuizId(Long quizId);
}
