package com.si;

public class ImageResponse {
    //'found:true/false, 'detected_plate'
    private boolean found;
    private String detected_plate;

    public ImageResponse(boolean found) {
        this.found = found;
    }

    public ImageResponse(boolean found, String detected_plate) {
        this.found = found;
        this.detected_plate = detected_plate;
    }

    public boolean isFound() {
        return found;
    }

    public String getDetected_plate() {
        return detected_plate;
    }
}
