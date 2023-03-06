package com.example.fastcampusmysql.domain.follow.service;

import com.example.fastcampusmysql.domain.follow.entity.Follow;
import com.example.fastcampusmysql.domain.follow.repository.FollowRepository;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class FollowWriteServiceTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    FollowRepository followRepository;

    @Test
    @DisplayName("Follower 저장 테스트")
    void create() {
        //given
        var fromMember = Member.builder()
                .email("abc@naver.com")
                .nickname("test1")
                .birthDay(LocalDate.now())
                .build();
        var toMember = Member.builder()
                .email("abc@naver.com")
                .nickname("test2")
                .birthDay(LocalDate.now())
                .build();
        memberRepository.save(fromMember);
        memberRepository.save(toMember);
        Follow follow = Follow.builder()
                .fromMember(fromMember)
                .toMember(toMember)
                .build();
        //when
        Follow savedFollow = followRepository.save(follow);

        //then
        assertThat(savedFollow.getFromMember()).isEqualTo(fromMember);
        assertThat(savedFollow.getToMember()).isEqualTo(toMember);
    }
}