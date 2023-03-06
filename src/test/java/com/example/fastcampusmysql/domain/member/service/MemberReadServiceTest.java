package com.example.fastcampusmysql.domain.member.service;

import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.entity.MemberNicknameHistory;
import com.example.fastcampusmysql.domain.member.repository.MemberNicknameHistoryRepository;
import com.example.fastcampusmysql.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PrePersist;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;

@DataJpaTest
class MemberReadServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberNicknameHistoryRepository memberNicknameHistoryRepository;

    @Test
    @DisplayName("회원 1명 조회 테스트")
    void getMember() {
        //given
        var member = Member.builder()
                    .email("abc@naver.com")
                    .nickname("test1")
                    .birthDay(LocalDate.now())
                    .build();
        memberRepository.save(member);

        //when
        Member findMember = memberRepository.findById(member.getId()).orElseThrow();

        //then
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    @DisplayName("회원 nickname변경 히스토리 조회 테스트")
    void getNicknameHistory() {
        //given
        var member = Member.builder()
                .email("abc@naver.com")
                .nickname("test1")
                .birthDay(LocalDate.now())
                .build();
        memberRepository.save(member);
        var memberNicknameHistory1 = MemberNicknameHistory.builder().member(member).nickname(member.getNickname()).build();
        var memberNicknameHistory2 = MemberNicknameHistory.builder().member(member).nickname(member.getNickname()).build();
        memberNicknameHistoryRepository.save(memberNicknameHistory1);
        memberNicknameHistoryRepository.save(memberNicknameHistory2);

        //when
        List<MemberNicknameHistory> memberNicknameHistorys = memberNicknameHistoryRepository.findAllByMemberId(member.getId());

        //then
        assertThat(memberNicknameHistorys).hasSize(2);
    }

    @Test
    @DisplayName("회원 여러명 조회 테스트 대상 없는경우")
    void getMembersNoTargetMember() {
        //given
        var member1 = Member.builder()
                .email("abc@naver.com")
                .nickname("test1")
                .birthDay(LocalDate.now())
                .build();
        var member2 = Member.builder()
                .email("abc@naver.com")
                .nickname("test1")
                .birthDay(LocalDate.now())
                .build();
        memberRepository.save(member1);
        memberRepository.save(member2);
        List<Long> list = new ArrayList<>();
        list.add(-1L);

        //when
        var result = memberRepository.findAllByIdIn(list);

        //then
        assertThat(result).isEmpty();

    }
    @Test
    @DisplayName("회원 여러명 조회 테스트 대상 있는경우")
    void getMembers() {
        //given
        var member1 = Member.builder()
                .email("abc@naver.com")
                .nickname("test1")
                .birthDay(LocalDate.now())
                .build();
        var member2 = Member.builder()
                .email("abc@naver.com")
                .nickname("test1")
                .birthDay(LocalDate.now())
                .build();
        memberRepository.save(member1);
        memberRepository.save(member2);
        List<Long> list = new ArrayList<>();
        list.add(member1.getId());
        list.add(member2.getId());

        //when
        var result = memberRepository.findAllByIdIn(list);

        //then
        assertThat(result).hasSize(2);
    }
}