package com.example.fastcampusmysql.domain.follow.entity;

import com.example.fastcampusmysql.domain.common.BaseEntity;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_member")
    private Member fromMember;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_member")
    private Member toMember;

    @Builder
    public Follow(Long id, Member fromMember, Member toMember) {
        this.id = id;
        this.fromMember = Objects.requireNonNull(fromMember);
        this.toMember = Objects.requireNonNull(toMember);
    }
}
