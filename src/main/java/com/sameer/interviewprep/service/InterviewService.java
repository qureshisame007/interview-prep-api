package com.sameer.interviewprep.service;

import com.sameer.interviewprep.dto.EvaluateRequest;
import com.sameer.interviewprep.dto.Feedback;
import com.sameer.interviewprep.dto.QuestionRequest;
import com.sameer.interviewprep.dto.QuestionsResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewService {

    private static final String QUESTIONS_PROMPT = """
            You are an experienced technical interviewer.
            Generate 5 interview questions for a {level} {role} position.
            Keep each question clear and specific.
            """;

    private static final String EVALUATE_PROMPT = """
            You are an interview coach. Evaluate the candidate's answer.

            Question: {question}
            Answer: {answer}

            Give a score from 1 to 10, short feedback, and an improved version of the answer.
            """;

    private final ChatClient chatClient;

    // ChatClient.Builder is auto-configured by Spring AI (uses the Gemini model from application.properties)
    public InterviewService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    /** Prompt Template: {level} and {role} are filled in at runtime. */
    public QuestionsResponse generateQuestions(QuestionRequest request) {
        List<String> questions = chatClient.prompt()
                .user(u -> u.text(QUESTIONS_PROMPT)
                        .param("level", request.level())
                        .param("role", request.role()))
                .call()
                .entity(new ParameterizedTypeReference<List<String>>() {});
        return new QuestionsResponse(questions);
    }

    /** Structured Output: Gemini's reply is mapped directly into the Feedback record. */
    public Feedback evaluate(EvaluateRequest request) {
        return chatClient.prompt()
                .user(u -> u.text(EVALUATE_PROMPT)
                        .param("question", request.question())
                        .param("answer", request.answer()))
                .call()
                .entity(Feedback.class);
    }
}
