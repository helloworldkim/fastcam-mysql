package com.example.fastcampusmysql.domain.post.service;

import com.example.fastcampusmysql.domain.post.dto.DailyPostCount;
import com.example.fastcampusmysql.domain.post.dto.DailyPostCountRequest;
import com.example.fastcampusmysql.domain.post.dto.PostDto;
import com.example.fastcampusmysql.domain.post.entity.Post;
import com.example.fastcampusmysql.domain.post.repository.PostJdbcRepository;
import com.example.fastcampusmysql.domain.post.repository.PostRepository;
import com.example.fastcampusmysql.domain.postlike.repository.PostlikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostReadService {
    private final PostRepository postRepository;
    private final PostlikeRepository postlikeRepository;

    public List<DailyPostCount> getDailyPostCounts(DailyPostCountRequest reqeust) {
//        return postRepository.groupByCreatedDate(reqeust);
        return null;
    }

    public Page<PostDto> getPostsByPage(Long memberId, Pageable pageable) {
        return postRepository.findAllByMemberId(memberId, pageable).map(this::toDto);
    }
    private PostDto  toDto(Post post) {
        return new PostDto(
                post.getId(),
                post.getContents(),
                postlikeRepository.count(post.getId()));

    }

    public Post getPost(Long postId) {
        return postRepository.findById(postId).orElseThrow();
    }

    public Slice<Post> getPostsBySlice(Long memberId, Pageable pageable) {
        return postRepository.findAllByMemberId(memberId, pageable);
    }

    public List<Post> getPosts(List<Long> ids) {
        return postRepository.findAllByInId(ids);
    }


}
