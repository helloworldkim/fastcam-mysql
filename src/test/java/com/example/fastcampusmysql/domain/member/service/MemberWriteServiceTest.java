package com.example.fastcampusmysql.domain.member.service;

import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.entity.MemberNicknameHistory;
import com.example.fastcampusmysql.domain.member.repository.MemberNicknameHistoryRepository;
import com.example.fastcampusmysql.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberWriteServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberNicknameHistoryRepository memberNicknameHistoryRepository;

    @Test
    @DisplayName("회원 저장 테스트")
    void register() {
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
    @DisplayName("회원 닉네임 변경 테스트")
    void changeNickname() {
        //given
        String changedNickname = "test_1";
        var member = Member.builder()
                .email("abc@naver.com")
                .nickname("test1")
                .birthDay(LocalDate.now())
                .build();
        memberRepository.save(member);
        Member findMember = memberRepository.findById(member.getId()).orElseThrow();
        findMember.changeNickname(changedNickname);

        //when
        var history = MemberNicknameHistory.builder()
                .member(member)
                .nickname(member.getNickname())
                .build();
        memberNicknameHistoryRepository.save(history);

        //then
        assertThat(member.getNickname()).isEqualTo(findMember.getNickname());
        assertThat(history.getNickname()).isEqualTo(findMember.getNickname());
    }
}