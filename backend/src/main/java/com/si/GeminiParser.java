package com.si;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class GeminiParser {

    public GeminiResult parse(String json) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        String text = getText(json).replace("```json", "")
                .replace("```", "")
                .trim();

        return mapper.readValue(text, GeminiResult.class);
    }

    public ImageResponse parseImageResponse(String json) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        String text = getText(json).replace("```json", "")
                .replace("```", "")
                .trim();

        return mapper.readValue(text, ImageResponse.class);
    }

    public PhotoChangeResponse parsePhotoResponse(String json) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        String text = getText(json).replace("```json", "")
                .replace("```", "")
                .trim();

        return mapper.readValue(text, PhotoChangeResponse.class);
    }

    private String getText(String json) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);
        return root.path("candidates")
                .get(0)
                .path("content")
                .path("parts")
                .get(0)
                .path("text")
                .asText();
    }
}
