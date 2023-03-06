package com.example.fastcampusmysql.domain.follow.service;

import com.example.fastcampusmysql.domain.follow.entity.Follow;
import com.example.fastcampusmysql.domain.follow.repository.FollowRepository;
import com.example.fastcampusmysql.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowReadService {

    private final FollowRepository followRepository;

    public List<Follow> getFollowings(Member member) {
        return followRepository.findAllByFromMember(member);
    }

    public List<Follow> getFollowers(Member member) {
        return followRepository.findAllByToMember(member);
    }



}
