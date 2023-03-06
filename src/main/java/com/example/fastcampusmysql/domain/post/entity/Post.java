package com.example.fastcampusmysql.domain.post.entity;

import com.example.fastcampusmysql.domain.common.BaseEntity;
import com.example.fastcampusmysql.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    private String contents;
    private Long likeCount;
    @Version
    private Long version;

    @Builder
    public Post(Long id, Member member, String contents, Long likeCount, Long version) {
        this.id = id;
        this.member = Objects.requireNonNull(member);
        this.contents = Objects.requireNonNull(contents);
        this.likeCount = likeCount == null ? 0 : likeCount;
        this.version = version == null ? 0 : version;
    }

    public void incrementLikeCount() {
        likeCount += 1;
    }
}
