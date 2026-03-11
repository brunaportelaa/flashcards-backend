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

        ReviewStatsEmbeddable emb = new ReviewStatsEmbeddable();

        emb.setRepetitions(stats.getRepetitions());
        emb.setIntervalDays(stats.getIntervalDays());
        emb.setEaseFactor(stats.getEaseFactor());
        emb.setNextReviewDate(stats.getNextReviewDate());
        emb.setSuccessCount(stats.getSuccessCount());
        emb.setFailCount(stats.getFailCount());

        entity.setReviewStats(emb);

        return entity;
    }

    public static Flashcard toDomain(FlashcardEntity entity) {

        ReviewStatsEmbeddable emb = entity.getReviewStats();

        ReviewStats stats = new ReviewStats(
                emb.getRepetitions(),
                emb.getIntervalDays(),
                emb.getEaseFactor(),
                emb.getNextReviewDate(),
                emb.getSuccessCount(),
                emb.getFailCount()
        );

        return Flashcard.rehydrate(
                entity.getId(),
                entity.getFront(),
                entity.getBack(),
                stats
        );
    }
}
