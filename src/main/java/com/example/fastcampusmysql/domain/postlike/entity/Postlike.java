package com.example.fastcampusmysql.domain.postlike.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class Postlike {

    private final Long id;
    private final Long memberId;
    private final Long postId;
    private final LocalDateTime createdAt;

    @Builder
    public Postlike(Long id, Long memberId, Long postId, LocalDateTime createdAt) {
        this.id = id;
        this.memberId = Objects.requireNonNull(memberId);
        this.postId = Objects.requireNonNull(postId);
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }
}
