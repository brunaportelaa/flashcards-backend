package com.project.flashcards.domain.model;

import java.time.LocalDate;

public class ReviewStats {

    private int repetitions;
    private int intervalDays;
    private double easeFactor;
    private LocalDate nextReviewDate;
    private int successCount;
    private int failCount;

    public ReviewStats() {
        this.repetitions = 0;
        this.intervalDays = 0;
        this.easeFactor = 2.5;
        this.nextReviewDate = LocalDate.now();
    }

    public void registerReview(ReviewGrade grade) {

        // Se a ReviewGrade == AGAIN, repetitions são zeradas e o intervalo de dias é setado para 1. failCount é incrementada.
        if (grade == ReviewGrade.AGAIN) {
            repetitions = 0;
            intervalDays = 1;
            failCount++;
        } else {
            successCount++;
            repetitions++;

            switch (grade) {
                case EASY -> intervalDays = (intervalDays == 0 ) ? 7 : intervalDays * 3;
                case GOOD -> intervalDays = (intervalDays == 0) ? 4 : intervalDays * 2;
                case HARD -> intervalDays = 2;
            }
        }

        nextReviewDate = LocalDate.now().plusDays(intervalDays);
    }

    public boolean isDue() {
        return !LocalDate.now().isBefore(nextReviewDate);
    }

    public LocalDate getNextReviewDate() {
        return nextReviewDate;
    }

    public int getIntervalDays() {
        return intervalDays;
    }
}
