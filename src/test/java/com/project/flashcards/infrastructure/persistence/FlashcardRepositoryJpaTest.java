package com.project.flashcards.infrastructure.persistence;

import com.project.flashcards.domain.model.Flashcard;
import com.project.flashcards.domain.repository.FlashcardRepository;
import com.project.flashcards.infrastructure.persistence.repository.FlashcardRepositoryImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FlashcardRepositoryJpaTest {
    FlashcardRepository repository = new FlashcardRepositoryImpl();

    @Test
    void shouldPersistFlashcard() {
        Flashcard card = new Flashcard("bonjour", "olá");
        repository.save(card);
        assertTrue(repository.findById(card.getId()).isPresent());

        Flashcard loaded = repository.findById(card.getId()).orElseThrow();

        assertEquals("bonjour", loaded.getFront());
        assertEquals("olá", loaded.getBack());
    }
}
