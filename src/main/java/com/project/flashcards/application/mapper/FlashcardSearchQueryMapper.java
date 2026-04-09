package com.project.flashcards.application.mapper;

import com.project.flashcards.application.dto.FlashcardSortField;
import com.project.flashcards.application.dto.SortDirection;
import com.project.flashcards.application.query.FlashcardSearchQuery;

public class FlashcardSearchQueryMapper {

    public static FlashcardSearchQuery from(
            int page,
            int size,
            String sort,
            String direction
    ) {
        return new FlashcardSearchQuery(
                page,
                size,
                parseSortField(sort),
                parseDirection(direction)
        );
    }

    private static FlashcardSortField parseSortField(String sort) {
        if (sort == null || sort.isBlank()) {
            return FlashcardSortField.FRONT;
        }

        return switch (sort.toLowerCase()) {
            case "front" -> FlashcardSortField.FRONT;
            case "back" -> FlashcardSortField.BACK;
            case "level" -> FlashcardSortField.LEVEL;
            case "nextreviewdate" -> FlashcardSortField.NEXT_REVIEW_DATE;
            default -> throw new IllegalArgumentException("Invalid sort field: " + sort);
        };
    }

    private static SortDirection parseDirection(String direction) {
        if (direction == null || direction.isBlank()) {
            return SortDirection.ASC;
        }

        return switch (direction.toLowerCase()) {
            case "asc" -> SortDirection.ASC;
            case "desc" -> SortDirection.DESC;
            default -> throw new IllegalArgumentException("Invalid sort direction: " + direction);
        };
    }
}
