package com.project.flashcards.infrastructure.persistence.entity;

import com.project.flashcards.domain.model.Flashcard;
import com.project.flashcards.domain.model.ReviewStats;

import java.util.HashSet;

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
        entity.setTags(new HashSet<>(card.getTags()));

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

        Flashcard card = Flashcard.rehydrate(
                entity.getId(),
                entity.getFront(),
                entity.getBack(),
                stats);

        entity.getTags().forEach(card::addTag);

        return card;
    }
}
