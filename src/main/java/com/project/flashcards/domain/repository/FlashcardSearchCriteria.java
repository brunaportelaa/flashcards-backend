package com.project.flashcards.domain.repository;

public class FlashcardSearchCriteria {

    private final int page;
    private final int size;
    private final String sortField;
    private final String sortDirection;
    private final String tag;
    private final boolean dueToday;

    public FlashcardSearchCriteria(
            int page,
            int size,
            String sortField,
            String sortDirection,
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

    public String getSortField() {
        return sortField;
    }

    public String getSortDirection() {
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