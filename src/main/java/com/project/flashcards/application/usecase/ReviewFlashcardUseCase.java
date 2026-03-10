package com.project.flashcards.application.usecase;

import com.project.flashcards.application.dto.ReviewFlashcardRequest;
import com.project.flashcards.domain.model.Flashcard;
import com.project.flashcards.domain.model.ReviewGrade;
import com.project.flashcards.domain.repository.FlashcardRepository;
import jakarta.ws.rs.NotFoundException;

import java.util.UUID;

public class ReviewFlashcardUseCase {

    private final FlashcardRepository repository;

    public ReviewFlashcardUseCase (FlashcardRepository repository) {
        this.repository = repository;
    }

    public void execute(UUID cardId, ReviewFlashcardRequest request) {

        Flashcard card = repository.findById(cardId)
                .orElseThrow(() -> new NotFoundException("Flashcard não encontrado"));

        ReviewGrade grade = ReviewGrade.valueOf(request.grade);

        card.review(grade);

        repository.save(card);
    }
}
