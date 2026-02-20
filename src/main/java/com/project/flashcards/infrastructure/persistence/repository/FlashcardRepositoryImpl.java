package com.project.flashcards.infrastructure.persistence.repository;

import com.project.flashcards.domain.model.Flashcard;
import com.project.flashcards.domain.repository.FlashcardRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FlashcardRepositoryImpl implements FlashcardRepository {
    @Override
    public Flashcard save(Flashcard flashcard) {
        return null;
    }

    @Override
    public Optional<Flashcard> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public List<Flashcard> findAll() {
        return List.of();
    }

    @Override
    public void delete(UUID id) {

    }
}
