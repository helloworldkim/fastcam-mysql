package com.example.fastcampusmysql.domain.follow.service;

import com.example.fastcampusmysql.domain.follow.entity.Follow;
import com.example.fastcampusmysql.domain.follow.repository.FollowRepository;
import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class FollowWriteService {

    private final FollowRepository followRepository;
    private final MemberRepository memberRepository;

    public void create(MemberDto fromMember, MemberDto toMember) {

        Assert.isTrue(!fromMember.id().equals(toMember.id()), "From, To 회원이 동일합니다.");
        var findFromMember = memberRepository.findById(fromMember.id()).orElseThrow();
        var findToMember = memberRepository.findById(toMember.id()).orElseThrow();

        var follow = Follow.builder()
                .fromMember(findFromMember)
                .toMember(findToMember)
                .build();

        followRepository.save(follow);


    }
}
