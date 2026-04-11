package com.project.flashcards.domain.model;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class Flashcard {

    private final UUID id;
    private String front;
    private String back;
    String example;
    String notes;
    String level;

    private Set<String> tags = new HashSet<>();

    private ReviewStats reviewStats;

    // Cria novo flashcard
    public Flashcard(String front, String back, String example, String notes, String level) {
        this(UUID.randomUUID(), front, back, new ReviewStats(), example, notes, level);
    }


    public Flashcard(UUID id, String front, String back, ReviewStats stats, String example, String notes,
                     String level ) {
        this.id = id;
        this.front = front;
        this.back = back;
        this.reviewStats = stats;
        this.example = example;
        this.notes = notes;
        this.level = level;
    }


    // Estrutura um flashcard existente que veio do banco, reconstruindo o objeto com dados persistidos
    public static Flashcard rehydrate(
            UUID id,
            String front,
            String back,
            ReviewStats stats,
            String example,
            String notes,
            String level
    ) {
       return new Flashcard(id, front, back, stats, example, notes, level);
    }


    public void addTag(String tag) {
        tags.add(tag.toLowerCase());
    }

    public void removeTag(String tag) {
        tags.remove(tag.toLowerCase());
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

    public Set<String> getTags() {
        return tags;
    }

    public String getExample() {
        return example;
    }

    public String getNotes() {
        return notes;
    }

    public String getLevel() {
        return level;
    }
}
