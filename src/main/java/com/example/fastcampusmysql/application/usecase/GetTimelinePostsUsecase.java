package com.example.fastcampusmysql.application.usecase;

import com.example.fastcampusmysql.domain.follow.entity.Follow;
import com.example.fastcampusmysql.domain.follow.service.FollowReadService;
import com.example.fastcampusmysql.domain.post.entity.Post;
import com.example.fastcampusmysql.domain.post.service.PostReadService;
import com.example.fastcampusmysql.util.CursorRequest;
import com.example.fastcampusmysql.util.PageCuorsor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetTimelinePostsUsecase {

    private final FollowReadService followReadService;
    private final PostReadService postReadService;

    public PageCuorsor<Post> excute(Long memberId, CursorRequest cursorRequest) {
        /**
         * 1. memberId -> follow 조회
         * 2. 1번 결과로 게시물 조회
         */
        var followings = followReadService.getFollowings(memberId);
        var followingMemberIds = followings.stream().map(follow -> follow.getToMemberId()).toList();
        return postReadService.getPosts(followingMemberIds, cursorRequest);
    }
}
