package com.si.servis;

import com.nimbusds.jose.shaded.gson.Gson;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Service
public class WebService {

    private final WebClient config;

    public WebService(WebClient config) {
        this.config = config;
    }

    public Map<String, String> isValidSentence(String language, String sentence) {

        String result = config.get()
                .uri("/api/v1/gemini/" + language + "/" + sentence)
                .retrieve()
                .bodyToFlux(String.class)
                .blockFirst();
        Gson gson = new Gson();
        Map<String, String> map = gson.fromJson(result, Map.class);
        return map;
    }

    public ImageResponse isValidNumber(String number, byte[] image) {

        Map<String, Object> body = new HashMap<>();
        body.put("number", number);
        body.put("image", image);

        ImageResponse result = config.post()
                .uri("/api/v1/gemini/plate")
                .bodyValue(body)
                .retrieve()
                .bodyToFlux(ImageResponse.class)
                .blockFirst();
        return result;
    }
    public PhotoToChange isValidChange(String sentence, byte[] imageToChange) {

        Map<String, Object> body = new HashMap<>();
        body.put("imageChange", imageToChange);
        body.put("sentenseToChange", sentence);

        PhotoToChange result = config.post()
                .uri("/api/v1/gemini/photo")
                .bodyValue(body)
                .retrieve()
                .bodyToFlux(PhotoToChange.class)
                .blockFirst();
        return result;
    }
}