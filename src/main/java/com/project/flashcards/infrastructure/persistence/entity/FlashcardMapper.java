package com.project.flashcards.infrastructure.persistence.entity;

import com.project.flashcards.domain.model.Flashcard;
import com.project.flashcards.domain.model.ReviewStats;

public class FlashcardMapper {

    public static FlashcardEntity toEntity(Flashcard card) {

        FlashcardEntity entity = new FlashcardEntity();

        entity.setId(card.getId());
        entity.setFront(card.getFront());
        entity.setBack(card.getBack());

        ReviewStats stats = card.getReviewStats();

        entity.setRepetitions(stats.getRepetitions());
        entity.setIntervalDays(stats.getIntervalDays());
        entity.setEaseFactor(stats.getEaseFactor());
        entity.setNextReviewDate(stats.getNextReviewDate());
        entity.setSuccessCount(stats.getSuccessCount());
        entity.setFailCount(stats.getFailCount());

        return new FlashcardEntity();
    }

    public static Flashcard toDomain(FlashcardEntity entity) {

        Flashcard card = new Flashcard(entity.getFront(), entity.getBack());
        //Fazer a reconstrução completa

        return card;
    }
}
