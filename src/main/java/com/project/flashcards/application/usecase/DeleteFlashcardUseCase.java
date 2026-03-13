package com.project.flashcards.application.usecase;

import com.project.flashcards.application.dto.CreateFlashcardRequest;
import com.project.flashcards.application.dto.FlashcardResponse;
import com.project.flashcards.application.mapper.FlashcardDtoMapper;
import com.project.flashcards.domain.model.Flashcard;
import com.project.flashcards.domain.repository.FlashcardRepository;
import jakarta.ws.rs.NotFoundException;

import java.util.UUID;

public class DeleteFlashcardUseCase {

    private final FlashcardRepository repository;

    public DeleteFlashcardUseCase(FlashcardRepository repository) {
        this.repository = repository;
    }

    public void execute(UUID id) {
        repository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Flashcard não encontrado"));

        repository.delete(id);
    }
}
