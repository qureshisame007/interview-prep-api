package com.sameer.interviewprep.controller;

import com.sameer.interviewprep.dto.EvaluateRequest;
import com.sameer.interviewprep.dto.Feedback;
import com.sameer.interviewprep.dto.QuestionRequest;
import com.sameer.interviewprep.dto.QuestionsResponse;
import com.sameer.interviewprep.service.InterviewService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class InterviewController {

    private final InterviewService service;

    public InterviewController(InterviewService service) {
        this.service = service;
    }

    @PostMapping("/questions")
    public QuestionsResponse questions(@RequestBody QuestionRequest request) {
        return service.generateQuestions(request);
    }

    @PostMapping("/evaluate")
    public Feedback evaluate(@RequestBody EvaluateRequest request) {
        return service.evaluate(request);
    }
}
