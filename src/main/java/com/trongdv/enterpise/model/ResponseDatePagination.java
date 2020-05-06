package com.trongdv.enterpise.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDatePagination<T> implements Serializable {

    private int status;
    private String message;
    private T data;
    private Page page;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Page {
        private int page;
        private int limit;
        private int totalPages;
        private long totalItems;
    }
}
