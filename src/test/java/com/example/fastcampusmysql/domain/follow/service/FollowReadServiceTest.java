package com.example.fastcampusmysql.domain.follow.service;

import com.example.fastcampusmysql.domain.follow.entity.Follow;
import com.example.fastcampusmysql.domain.follow.repository.FollowRepository;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
class FollowReadServiceTest {
    @Autowired
    FollowRepository followRepository;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("Following한 대상 리스트 조회")
    void getFollowings() {
        //given
        // 1번,3번 유저가 2번 유저를 following
        //1번
        var fromMember1 = Member.builder()
                .email("abc@naver.com")
                .nickname("test1")
                .birthDay(LocalDate.now())
                .build();
        //2번
        var toMember = Member.builder()
                .email("abc@naver.com")
                .nickname("test2")
                .birthDay(LocalDate.now())
                .build();
        //3번
        var fromMember2 = Member.builder()
                .email("abc@naver.com")
                .nickname("test1")
                .birthDay(LocalDate.now())
                .build();
        memberRepository.save(fromMember1);
        memberRepository.save(toMember);
        memberRepository.save(fromMember2);

        Follow follow1 = Follow.builder()
                .fromMember(fromMember1)
                .toMember(toMember)
                .build();
        followRepository.save(follow1);
        Follow follow2 = Follow.builder()
                .fromMember(fromMember2)
                .toMember(toMember)
                .build();
        followRepository.save(follow2);

        //when
        List<Follow> result = followRepository.findAllByFromMember(fromMember1);

        //then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getFromMember()).isEqualTo(fromMember1);

    }

    @Test
    @DisplayName("나를 Follower한 대상 여러건 조회")
    void getFollowers() {
        //given
        // 1번,3번 유저가 2번 유저를 following
        //1번
        var fromMember1 = Member.builder()
                .email("abc@naver.com")
                .nickname("test1")
                .birthDay(LocalDate.now())
                .build();
        //2번
        var toMember = Member.builder()
                .email("abc@naver.com")
                .nickname("test2")
                .birthDay(LocalDate.now())
                .build();
        //3번
        var fromMember2 = Member.builder()
                .email("abc@naver.com")
                .nickname("test1")
                .birthDay(LocalDate.now())
                .build();
        memberRepository.save(fromMember1);
        memberRepository.save(toMember);
        memberRepository.save(fromMember2);

        Follow follow1 = Follow.builder()
                .fromMember(fromMember1)
                .toMember(toMember)
                .build();
        followRepository.save(follow1);
        Follow follow2 = Follow.builder()
                .fromMember(fromMember2)
                .toMember(toMember)
                .build();
        followRepository.save(follow2);

        //when
        List<Follow> result = followRepository.findAllByToMember(toMember);

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getToMember()).isEqualTo(toMember);
    }
}