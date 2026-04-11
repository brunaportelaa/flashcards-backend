package com.project.flashcards.application.usecase;

import com.project.flashcards.application.dto.StudyStatsResponse;
import com.project.flashcards.domain.model.Flashcard;
import com.project.flashcards.domain.repository.FlashcardRepository;

import java.util.List;

public class GetStudyStatsUseCase {

    private final FlashcardRepository repository;

    public GetStudyStatsUseCase(FlashcardRepository repository) {
        this.repository = repository;
    }

    public StudyStatsResponse execute() {

        List<Flashcard> cards = repository.findAll();

        int total = cards.size();

        int dueToday = (int) cards.stream()
                .filter(Flashcard::isDue)
                .count();

        int success = cards.stream()
                .mapToInt(c -> c.getReviewStats().getSuccessCount())
                .sum();

        int fail = cards.stream()
                .mapToInt(c -> c.getReviewStats().getFailCount())
                .sum();

        StudyStatsResponse stats = new StudyStatsResponse();

        stats.totalCards = total;
        stats.dueToday = dueToday;
        stats.totalReviews = success + fail;

        stats.successRate =
                stats.totalReviews == 0 ? 0 : (double) success / stats.totalReviews;

        return stats;
    }
}
