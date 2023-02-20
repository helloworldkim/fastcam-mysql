package com.example.fastcampusmysql.domain.member.entity;

import com.example.fastcampusmysql.domain.member.util.MemberFixtureFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @Test
    @DisplayName("임의의 랜덤객체를 만들 수 있다.")
    public void testMemberFixtureFactory() {
        LongStream.range(0, 10)
                .mapToObj(MemberFixtureFactory::create)
                .forEach(member -> {
                    System.out.println("member = " + member.getNickname());
                });

    }

    @Test
    @DisplayName("회원은 닉네임을 변경할 수 있다.")
    public void testChangeName() {

        var member = MemberFixtureFactory.create();
        var expacted = "test";
        member.changeNickname(expacted);

        assertThat(expacted).isEqualTo(member.getNickname());
    }

    @Test
    @DisplayName("회원은 닉네임을 10자를 초과할 수 없다.")
    public void testNicknameMaxLength() {

        var member = MemberFixtureFactory.create();
        var overMaxLength = "testtesttes";

        assertThrows(IllegalArgumentException.class, () -> member.changeNickname(overMaxLength));
    }

}