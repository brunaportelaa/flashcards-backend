package com.project.flashcards.application.usecase;

import com.project.flashcards.application.dto.StudyCardResponse;
import com.project.flashcards.application.mapper.StudyCardMapper;
import com.project.flashcards.domain.model.Flashcard;
import com.project.flashcards.domain.repository.FlashcardRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class GetStudyCardsUseCase {

    private final FlashcardRepository repository;

    public GetStudyCardsUseCase(FlashcardRepository repository) {
        this.repository = repository;
    }

    public List<StudyCardResponse> execute() {

        List<Flashcard> cards = repository.findDueCards(LocalDate.now());

        return cards.stream()
                .filter(Flashcard::isDue)
                .map(StudyCardMapper::toResponse)
                .collect(Collectors.toList());
    }
}
