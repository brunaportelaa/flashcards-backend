package com.project.flashcards.domain.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Flashcard {

    private final UUID id;
    private String front;
    private String back;
    private String example;
    private String notes;
    private String level;

    private Set<String> tags = new HashSet<>();
    private ReviewStats reviewStats;

    public Flashcard(String front, String back) {
        this.id = UUID.randomUUID();
        this.front = front;
        this.back = back;
        this.reviewStats = new ReviewStats();
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public void review(ReviewGrade grade) {
        reviewStats.registerReview(grade);
    }

    public boolean isDue() {
        return reviewStats.isDue();
    }

    public UUID getId() {
        return id;
    }

    public String getFront() {
        return front;
    }

    public String getBack() {
        return back;
    }

    public ReviewStats getReviewStats() {
        return reviewStats;
    }
}
