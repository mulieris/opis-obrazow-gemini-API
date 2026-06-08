package com.si;

public class GeminiResult {
    private String isCorrect;
    private String correctSentence;

    public GeminiResult() {
    }

    public GeminiResult(String correctSentence, String isCorrect) {
        this.correctSentence = correctSentence;
        this.isCorrect = isCorrect;
    }

    public String getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(String isCorrect) {
        this.isCorrect = isCorrect;
    }

    public String getCorrectSentence() {
        return correctSentence;
    }

    public void setCorrectSentence(String correctSentence) {
        this.correctSentence = correctSentence;
    }
}
