package com.example.fastcampusmysql.domain.postlike.service;

import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import com.example.fastcampusmysql.domain.post.entity.Post;
import com.example.fastcampusmysql.domain.postlike.entity.Postlike;
import com.example.fastcampusmysql.domain.postlike.repository.PostlikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostlikeWriteService {

    final private PostlikeRepository postLikeRepository;

    public Long create(Post post, MemberDto memberDto) {
        var postlike = Postlike
                .builder()
                .postId(post.getId())
                .memberId(memberDto.id())
                .build();

        return postLikeRepository.save(postlike).getPostId();
    }



}
