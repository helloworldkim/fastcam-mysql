package com.example.fastcampusmysql.controller;

import com.example.fastcampusmysql.application.usecase.CreatePostUsecace;
import com.example.fastcampusmysql.application.usecase.CreatePostlikeUsecase;
import com.example.fastcampusmysql.application.usecase.GetTimelinePostsUsecase;
import com.example.fastcampusmysql.domain.post.dto.DailyPostCount;
import com.example.fastcampusmysql.domain.post.dto.DailyPostCountRequest;
import com.example.fastcampusmysql.domain.post.dto.PostCommand;
import com.example.fastcampusmysql.domain.post.dto.PostDto;
import com.example.fastcampusmysql.domain.post.entity.Post;
import com.example.fastcampusmysql.domain.post.service.PostReadService;
import com.example.fastcampusmysql.domain.post.service.PostWriteService;
import com.example.fastcampusmysql.util.CursorRequest;
import com.example.fastcampusmysql.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostWriteService postWriteService;
    private final PostReadService postReadService;
    private final GetTimelinePostsUsecase getTimelinePostsUsecase;
    private final CreatePostUsecace createPostUsecace;
    private final CreatePostlikeUsecase createPostlikeUsecase;

    @PostMapping("")
    public Long register(PostCommand command) {
        return createPostUsecace.execute(command);
    }

    @GetMapping("/daily-post-counts")
    public List<DailyPostCount> getDailyPostCounts(DailyPostCountRequest request) {
        return postReadService.getDailyPostCounts(request);
    }

    @GetMapping("/members/{memberId}")
    public Page<PostDto> getPosts(
            @PathVariable("memberId") Long memberId,
            Pageable pagable
    ) {
        return postReadService.getPostsByPage(memberId, pagable);
    }


    @GetMapping("/members/{memberId}/by-cursor")
    public Slice<Post> getPostsByCursor(
            @PathVariable("memberId") Long memberId,
            Pageable pageable
    ) {
        return postReadService.getPostsBySlice(memberId, pageable);
    }

    @GetMapping("/members/{memberId}/timeline")
    public PageCursor<Post> getTimeline(
            @PathVariable("memberId") Long memberId,
            CursorRequest cursorRequest
    ) {
//        return getTimelinePostsUsecase.excuteByTimeline(memberId, cursorRequest);
        return null;
    }
    @PostMapping("/{postId}/like/v1")
    public void likePostByOptimisticLock(@PathVariable("postId") Long postId) {
        postWriteService.likePostByOptimisticLock(postId);
    }
    @PostMapping("/{postId}/like/v2")
    public void likePostByPessimisticLock(@PathVariable("postId") Long postId) {
        postWriteService.likePostByPessimisticLock(postId);
    }

    @PostMapping("/{postId}/like/v3")
    public void likePostV2(@PathVariable("postId") Long postId, @RequestParam Long memberId) {
        createPostlikeUsecase.execute(postId, memberId);
    }




}
