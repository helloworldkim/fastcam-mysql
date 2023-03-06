package com.example.fastcampusmysql.application.usecase;

import com.example.fastcampusmysql.domain.follow.service.FollowReadService;
import com.example.fastcampusmysql.domain.post.entity.Post;
import com.example.fastcampusmysql.domain.post.service.PostReadService;
import com.example.fastcampusmysql.domain.timeline.entity.Timeline;
import com.example.fastcampusmysql.domain.timeline.service.TimelineReadService;
import com.example.fastcampusmysql.util.CursorRequest;
import com.example.fastcampusmysql.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetTimelinePostsUsecase {

    private final FollowReadService followReadService;
    private final PostReadService postReadService;
    private final TimelineReadService timelineReadService;

//    public PageCursor<Post> excute(Long memberId, CursorRequest cursorRequest) {
//        /**
//         * 1. memberId -> follow 조회
//         * 2. 1번 결과로 게시물 조회
//         */
//        var followings = followReadService.getFollowings(memberId);
//        var followingMemberIds = followings.stream().map(follow -> follow.getToMemberId()).toList();
//        return postReadService.getPosts(followingMemberIds, cursorRequest);
//    }
//
//    public PageCursor<Post> excuteByTimeline(Long memberId, CursorRequest cursorRequest) {
//        /**
//         * 1. memberId -> follow 조회
//         * 2. 1번 결과로 게시물 조회
//         */
//        var pagedTimelines = timelineReadService.getTimelines(memberId, cursorRequest);
//        var postIds = pagedTimelines.body().stream().map(Timeline::getPostId).toList();
//        var posts = postReadService.getPosts(postIds);
//
//        return new PageCursor<>(pagedTimelines.nextCursorRequest(), posts);
//
//    }
}
