package com.project.flashcards.application.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Optional;
import java.util.Set;

public class CreateFlashcardRequest {
    @NotBlank(message = "Frente é obrigatória")
    public String front;

    @NotBlank(message = "Verso é obrigatório")
    public String back;

    public String example;
    public String notes;
    public String level;
    public Set<String> tags;
}
