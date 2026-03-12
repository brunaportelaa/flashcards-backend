package com.project.flashcards.application.usecase;

import com.project.flashcards.application.dto.CreateFlashcardRequest;
import com.project.flashcards.application.dto.FlashcardResponse;
import com.project.flashcards.application.mapper.FlashcardDtoMapper;
import com.project.flashcards.domain.model.Flashcard;
import com.project.flashcards.domain.repository.FlashcardRepository;

public class CreateFlashcardUseCase {

    private final FlashcardRepository repository;

    public CreateFlashcardUseCase(FlashcardRepository repository) {
        this.repository = repository;
    }

    public FlashcardResponse execute(CreateFlashcardRequest requestDTO) {
        Flashcard card = FlashcardDtoMapper.toDomain(requestDTO);
        repository.save(card);
        return FlashcardDtoMapper.toResponse(card);
    }
}
