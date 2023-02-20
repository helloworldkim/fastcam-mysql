package com.example.fastcampusmysql.domain.member.service;

import com.example.fastcampusmysql.domain.member.dto.RegisterMemberCommand;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberWriteService {

    private final MemberRepository memberRepository;

    public Member register(RegisterMemberCommand command) {


        var member = Member
                .builder()
                .nickname(command.nickname())
                .email(command.email())
                .birthDay(command.birthday())
                .build();
        return memberRepository.save(member);
    }

    public void changeNickname(Long id, String nickname) {

        var member =  memberRepository.findById(id).orElseThrow();
        member.changeNickname(nickname);
        memberRepository.save(member);
    }
}
