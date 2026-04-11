package com.project.flashcards.domain.repository;

import com.project.flashcards.domain.model.Flashcard;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FlashcardRepository {

    Flashcard save(Flashcard flashcard);

    Optional<Flashcard> findById(UUID id);

    List<Flashcard> findAll();

    List<Flashcard> search(FlashcardSearchCriteria criteria);

    long count(FlashcardSearchCriteria criteria);

    List<Flashcard> findDueCards(LocalDate today);

    void delete(UUID id);

    List<Flashcard> findByTag(String tag);

    List<Flashcard> findAllPaged(int page, int size, String sortField, String direction);

    List<Flashcard> findAllSortedByPriority();
}
