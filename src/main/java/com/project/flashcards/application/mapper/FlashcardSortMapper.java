package com.project.flashcards.application.mapper;

import com.project.flashcards.application.dto.FlashcardSortField;
import com.project.flashcards.application.dto.SortDirection;

public class FlashcardSortMapper {

    public static String toJpaField(FlashcardSortField field){
        return switch (field) {
            case FRONT -> "f.front";
            case BACK -> "f.back";
            case LEVEL -> "f.level";
            case NEXT_REVIEW_DATE -> "f.reviewStats.nextReviewDate";
        };
    }

    public static String toJpaDirection(SortDirection direction) {
        return switch (direction) {
            case ASC ->  "ASC";
            case DESC -> "DESC";
        };
    }
}
