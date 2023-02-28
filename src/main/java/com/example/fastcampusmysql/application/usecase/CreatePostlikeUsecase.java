package com.example.fastcampusmysql.application.usecase;

import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import com.example.fastcampusmysql.domain.member.service.MemberReadService;
import com.example.fastcampusmysql.domain.post.service.PostReadService;
import com.example.fastcampusmysql.domain.postlike.service.PostlikeWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePostlikeUsecase {
    private final PostReadService postReadService;
    private final MemberReadService memberReadService;
    private final PostlikeWriteService postlikeWriteService;

    public void execute(Long postId, Long memberId) {

        var post = postReadService.getPost(postId);
        var member = memberReadService.getMember(memberId);
        postlikeWriteService.create(post, member);


    }
}
