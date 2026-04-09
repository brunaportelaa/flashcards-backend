package com.project.flashcards.application.dto;

import java.util.List;

public class StudySessionResponse {

    public List<StudyCardResponse> items;
    public int totalCards;

    public StudySessionResponse() {
    }

    public StudySessionResponse(List<StudyCardResponse> items) {
        this.items = items;
        this.totalCards = items == null ? 0 : items.size();
    }
}
