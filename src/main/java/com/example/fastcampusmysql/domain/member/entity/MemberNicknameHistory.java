package com.example.fastcampusmysql.domain.member.entity;

import com.example.fastcampusmysql.domain.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberNicknameHistory extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "member_nickname_history_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    private String nickname;

    @Builder
    public MemberNicknameHistory(Long id, Member member, String nickname) {
        this.id = id;
        this.member = member;
        this.nickname = nickname;
    }
}
