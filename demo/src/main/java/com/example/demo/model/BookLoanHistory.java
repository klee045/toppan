package com.example.demo.model;

import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class BookLoanHistory {

    @Column(columnDefinition = "timestamp with time zone NOT NULL")
    private OffsetDateTime createdAt;

    @Column(columnDefinition = "timestamp with time zone NOT NULL")
    private OffsetDateTime updatedAt;

    public BookLoanHistory() {
    }

    public BookLoanHistory(OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public OffsetDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
