package com.project.flashcards.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "flashcards")
public class FlashcardEntity {

    @Id
    private UUID id;

    private String front;
    private String back;
    private String example;
    private String notes;
    private String level;

    // O embeddable não cria uma nova tabela com chave estrangeira, mas sim insere os campos do objeto Embedded
    // diretamente na tabela do objeto principal
    @Embedded
    private ReviewStatsEmbeddable reviewStats;

    // Define uma nova tabela no banco, que tem a coluna de tags, e tem o id dos Flashcards como chave estrangeira
    @ElementCollection
    @CollectionTable(
            name = "flashcard_tags",
            joinColumns = @JoinColumn(name = "flashcard_id")
    )
    private Set<String> tags = new HashSet<>();

    protected FlashcardEntity(){}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public ReviewStatsEmbeddable getReviewStats() {
        return reviewStats;
    }

    public void setReviewStats(ReviewStatsEmbeddable reviewStats) {
        this.reviewStats = reviewStats;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }
}
