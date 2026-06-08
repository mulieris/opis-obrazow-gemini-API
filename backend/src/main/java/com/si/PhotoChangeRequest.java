package com.si;

public class PhotoChangeRequest {
    private byte[] imageChange;
    private String sentenseToChange;

    public PhotoChangeRequest() {
    }
    public byte[] getImageChange() {
        return imageChange;
    }

    public void setimageChange(byte[] imageChange) {
        this.imageChange = imageChange;
    }

    public PhotoChangeRequest(String sentenseToChange, byte[] imageChange) {
        this.sentenseToChange = sentenseToChange;
        this.imageChange = imageChange;
    }
    public String getSenteceToChange() {
        return sentenseToChange;
    }

    public void setSentenseToChange(String sentenseToChange) {
        this.sentenseToChange = sentenseToChange;
    }

}
