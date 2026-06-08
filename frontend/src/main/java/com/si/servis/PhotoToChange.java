package com.si.servis;

public class PhotoToChange {
    private boolean found;
    private String answer;

    public PhotoToChange(boolean found, String answer) {
        this.found = found;
        this.answer = answer;
    }

    public PhotoToChange() {
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
