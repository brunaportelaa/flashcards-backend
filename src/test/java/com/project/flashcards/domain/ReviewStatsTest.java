package com.project.flashcards.domain;

import com.project.flashcards.domain.model.ReviewGrade;
import com.project.flashcards.domain.model.ReviewStats;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReviewStatsTest {

    @Test
    void shouldScheduleNextReviewWhenGood() {
        ReviewStats stats = new ReviewStats();
        stats.registerReview(ReviewGrade.GOOD);
        assertTrue(stats.getIntervalDays() > 0);
    }

    @Test
    void shouldResetWhenAgain() {
        ReviewStats stats = new ReviewStats();
        stats.registerReview(ReviewGrade.AGAIN);
        assertEquals(1, stats.getIntervalDays());
    }

}
