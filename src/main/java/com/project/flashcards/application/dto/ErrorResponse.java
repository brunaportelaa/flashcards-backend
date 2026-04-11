package com.project.flashcards.application.dto;

import java.time.LocalDateTime;

public class ErrorResponse {

    public String message;
    public LocalDateTime timestamp;

    public ErrorResponse(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
