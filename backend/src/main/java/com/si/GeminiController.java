package com.si;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/gemini/")
public class GeminiController {

    private final GeminiService service;

    public GeminiController(GeminiService service) {
        this.service = service;
    }

    @GetMapping("/{language}/{sentence}")
    public GeminiResult  getMessage(@PathVariable("language") String language,
                                    @PathVariable("sentence") String sentence) {

        return service.getMessage(language,sentence);
    }

    @PostMapping("/")
    public ImageResponse isValid(@RequestBody ImageRequest request) {

        return service.isCorrect(request.getImage(), request.getNumber());
    }

    @PostMapping("/plate")
    public ImageResponse plate(@RequestBody ImageRequest request) {

        return service.isCorrect(request.getImage(), request.getNumber());
    }

    @PostMapping("/photo")
    public PhotoChangeResponse photo(@RequestBody PhotoChangeRequest request) {

        return service.photoChange(request.getImageChange(), request.getSenteceToChange());
    }

}
