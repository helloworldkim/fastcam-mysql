package com.example.fastcampusmysql.application.usecase;


import com.example.fastcampusmysql.domain.follow.service.FollowWriteService;
import com.example.fastcampusmysql.domain.member.service.MemberReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateFollowMemberUsecase {

    private final MemberReadService memberReadService;
    private final FollowWriteService followWriteService;

    public void execute(Long fromMemberId, Long toMemberId) {
        /**
         * 1. 입력받은 memeberId로 조회
         * 2. FollowWriteService.create()
         */

        var fromMember = memberReadService.getMember(fromMemberId);
        var toMember = memberReadService.getMember(toMemberId);
        // TODO: 동일한 조건으로 중복 Follow 체크 필요
        followWriteService.create(fromMember, toMember);

    }
}
