package com.project.flashcards.application.usecase;

import com.project.flashcards.application.dto.FlashcardResponse;
import com.project.flashcards.application.mapper.FlashcardDtoMapper;
import com.project.flashcards.domain.model.Flashcard;
import com.project.flashcards.domain.repository.FlashcardRepository;

import java.util.List;

public class FindFlashcardByTagUseCase {

    private final FlashcardRepository repository;

    public FindFlashcardByTagUseCase(FlashcardRepository repository) {
        this.repository = repository;
    }

    public List<FlashcardResponse> execute(String tag) {

        List<Flashcard> cards = repository.findByTag(tag);

        return cards.stream()
                .map(FlashcardDtoMapper::toResponse)
                .toList();
    }
}
