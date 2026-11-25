# AI Interview Prep API

Spring Boot REST API that uses **Spring AI** and **Google Gemini** to generate interview questions and evaluate answers.

**Stack:** Java 25, Spring Boot, Spring AI, Google Gemini, Maven

## Spring AI features used
- **ChatClient** API for Gemini integration
- **Prompt Templates** (`{role}`, `{level}`, `{question}`, `{answer}`)
- **Structured Output** (Gemini response mapped to Java records)

## Run
```bash
export GEMINI_API_KEY=your_key_here      # Windows (PowerShell): $env:GEMINI_API_KEY="your_key_here"
mvn spring-boot:run
```

## Endpoints

### 1. Generate questions
`POST /api/questions`
```bash
curl -X POST http://localhost:8080/api/questions \
  -H "Content-Type: application/json" \
  -d '{"role":"Java Backend","level":"Junior"}'
```
Response:
```json
{ "questions": ["What is the difference between ...", "..."] }
```

### 2. Evaluate an answer
`POST /api/evaluate`
```bash
curl -X POST http://localhost:8080/api/evaluate \
  -H "Content-Type: application/json" \
  -d '{"question":"What is dependency injection?","answer":"It is when Spring gives objects their dependencies instead of creating them inside the class."}'
```
Response:
```json
{ "score": 7, "feedback": "Good basic idea ...", "improvedAnswer": "Dependency injection is ..." }
```

## Project structure
```
controller/InterviewController   -> REST endpoints
service/InterviewService         -> ChatClient, prompt templates, structured output
dto/                             -> Java records (request/response/Feedback)
```
