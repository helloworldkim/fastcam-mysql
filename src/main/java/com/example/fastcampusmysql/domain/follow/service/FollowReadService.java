package com.example.fastcampusmysql.domain.follow.service;

import com.example.fastcampusmysql.domain.follow.entity.Follow;
import com.example.fastcampusmysql.domain.follow.repository.FollowRepository;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowReadService {

    private final FollowRepository followRepository;
    private final MemberRepository memberRepository;

    public List<Follow> getFollowings(Long memberId) {
        var fromMember = memberRepository.findById(memberId).orElseThrow();
        return followRepository.findAllByFromMember(fromMember);
    }

    public List<Follow> getFollowers(Long memberId) {
        var toMember = memberRepository.findById(memberId).orElseThrow();
        return followRepository.findAllByToMember(toMember);
    }



}
