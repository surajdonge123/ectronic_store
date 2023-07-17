package com.bikkadit.ectronic_store.dto;

import java.util.List;

public class PageableResponse <T>{
    private List<T> contents;
    private int pageNumber;
    private int pageSize;
    private Long totalElement;
    private int totalPages;
    private boolean lastPage;
}
