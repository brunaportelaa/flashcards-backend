package com.project.flashcards.domain.repository;

import com.project.flashcards.domain.model.Flashcard;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FlashcardRepository {

    Flashcard save(Flashcard flashcard);

    Optional<Flashcard> findById(UUID id);

    List<Flashcard> findAll();

    void delete(UUID id);
}
