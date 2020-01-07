package com.pru_ybs.musicquiz;

class Question {
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private int answerNr;

    Question() {

    }

    Question(String question, String option1, String option2, String option3, int answerNr) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.answerNr = answerNr;
    }

    String getQuestion() {
        return question;
    }

    void setQuestion(String question) {
        this.question = question;
    }

    String getOption1() {
        return option1;
    }

    void setOption1(String option1) {
        this.option1 = option1;
    }

    String getOption2() {
        return option2;
    }

    void setOption2(String option2) {
        this.option2 = option2;
    }

    String getOption3() {
        return option3;
    }

    void setOption3(String option3) {
        this.option3 = option3;
    }

    int getAnswerNr() {
        return answerNr;
    }

    void setAnswerNr(int answerNr) {
        this.answerNr = answerNr;
    }
}
