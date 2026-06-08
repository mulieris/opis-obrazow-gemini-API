package com.si;

public class PhotoChangeResponse {
    private boolean found;
    private String answer;

    public PhotoChangeResponse() {
    }

    public PhotoChangeResponse(boolean found, String answer) {
        this.found = found;
        this.answer = answer;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
