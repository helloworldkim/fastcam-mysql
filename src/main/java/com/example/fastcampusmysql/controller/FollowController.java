package com.example.fastcampusmysql.controller;

import com.example.fastcampusmysql.application.usecase.CreateFollowMemberUsecase;
import com.example.fastcampusmysql.application.usecase.GetFollowingMembersUsecase;
import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follow")
public class FollowController {

    private final CreateFollowMemberUsecase createFollowMemberUsecase;
    private final GetFollowingMembersUsecase getFollowingMembersUsecase;

    /**
     * Follow 처리 fromId -> toId
     * @param fromId
     * @param toId
     */
    @PostMapping("/{fromId}/{toId}")
    public void register(@PathVariable Long fromId, @PathVariable Long toId) {
        createFollowMemberUsecase.execute(fromId, toId);
    }

    /**
     * 특정 회원을 following 하고있는 대상 리스트 조회
     * @param fromId
     * @return
     */
    @GetMapping("/members/{fromId}")
    public List<MemberDto> followingMemberList(@PathVariable Long fromId) {
        return getFollowingMembersUsecase.execute(fromId);
    }



}
