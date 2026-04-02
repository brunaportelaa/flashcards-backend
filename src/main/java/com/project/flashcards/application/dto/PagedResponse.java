package com.project.flashcards.application.dto;

import java.util.List;

public class PagedResponse<T> {

    public List<T> items;
    public int page;
    public int size;
    public long totalItems;
    public int totalPages;

    public PagedResponse() {}

    public PagedResponse(List<T> items, int page, int size, long totalItems) {
        this.items = items;
        this.page = page;
        this.size = size;
        this.totalItems = totalItems;
        this.totalPages = size == 0 ? 0 : (int) Math.ceil((double) totalItems / size);
    }
}
