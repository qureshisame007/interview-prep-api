package com.sameer.interviewprep.dto;

/**
 * Structured Output target: Spring AI maps Gemini's JSON reply into this record.
 */
public record Feedback(int score, String feedback, String improvedAnswer) {
}
