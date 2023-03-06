package com.example.fastcampusmysql.domain.post.service;

import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.repository.MemberRepository;
import com.example.fastcampusmysql.domain.post.entity.Post;
import com.example.fastcampusmysql.domain.post.dto.PostCommand;
import com.example.fastcampusmysql.domain.post.repository.PostJdbcRepository;
import com.example.fastcampusmysql.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostWriteService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public Long create(PostCommand command) {
        Member member = memberRepository.findById(command.memberId()).orElseThrow();
        var post = Post.builder()
                .member(member)
                .contents(command.contents())
                .build();

        return postRepository.save(post).getId();
    }

    public void likePostByPessimisticLock(Long postId) {
        var post = postRepository.findByIdWithPessimisticLock(postId).orElseThrow();
        post.incrementLikeCount();
    }
    public void likePostByOptimisticLock(Long postId) {
        var post = postRepository.findByIdWithOptimisticLock(postId).orElseThrow();
        post.incrementLikeCount();
    }

}
