package com.project.flashcards.domain.repository;

import com.project.flashcards.application.dto.FlashcardSortField;
import com.project.flashcards.application.dto.SortDirection;

public class FlashcardSearchCriteria {

    private final int page;
    private final int size;
    private final FlashcardSortField sortField;
    private final SortDirection sortDirection;
    private final String tag;
    private final boolean dueToday;

    public FlashcardSearchCriteria(
            int page,
            int size,
            FlashcardSortField sortField,
            SortDirection sortDirection,
            String tag,
            boolean dueToday
    ) {
        this.page = page;
        this.size = size;
        this.sortField = sortField;
        this.sortDirection = sortDirection;
        this.tag = tag;
        this.dueToday = dueToday;
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

    public String getTag() {
        return tag;
    }

    public boolean isDueToday() {
        return dueToday;
    }

    public boolean hasTagFilter() {
        return tag != null && !tag.isBlank();
    }
}