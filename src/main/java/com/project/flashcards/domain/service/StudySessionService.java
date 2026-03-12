package com.project.flashcards.domain.service;

import com.project.flashcards.domain.model.Flashcard;

import java.util.List;
import java.util.stream.Collectors;

public class StudySessionService {

    public List<Flashcard> getDueCards(List<Flashcard> cards) {
        return cards.stream()
                .filter(Flashcard::isDue)
                .collect(Collectors.toList());
    }
}
