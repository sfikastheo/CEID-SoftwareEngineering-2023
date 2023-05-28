package com.live_the_city;

public class Question {
    
    Quiz quiz;
    private int questionId;
    private String question;
    private String answer;
    private String file_path;


    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
    
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }



    public Question() {
    }


    public Question(Quiz quiz, int questionId, String question, String answer) {
        this.quiz = quiz;
        this.questionId = questionId;
        this.question = question;
        this.answer = answer;
    }

}
