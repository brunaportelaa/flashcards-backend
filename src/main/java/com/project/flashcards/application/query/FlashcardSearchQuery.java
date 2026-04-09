package com.project.flashcards.application.query;

import com.project.flashcards.application.dto.FlashcardSortField;
import com.project.flashcards.application.dto.SortDirection;

public class FlashcardSearchQuery {

    private final int page;
    private final int size;
    private final FlashcardSortField sortField;
    private final SortDirection sortDirection;

    public FlashcardSearchQuery(int page, int size, FlashcardSortField sortField, SortDirection sortDirection) {
        this.page = page;
        this.size = size;
        this.sortField = sortField;
        this.sortDirection = sortDirection;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public FlashcardSortField getSortField() {
        return sortField;
    }

    public SortDirection getSortDirection() {
        return sortDirection;
    }
}
