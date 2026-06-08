package com.si.servis;

public class ImageResponse {
    private boolean found;
    private String detected_plate;

    public ImageResponse(boolean found, String detected_plate) {
        this.found = found;
        this.detected_plate = detected_plate;
    }

    public ImageResponse() {
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public String getDetected_plate() {
        return detected_plate;
    }

    public void setDetected_plate(String detected_plate) {
        this.detected_plate = detected_plate;
    }
}