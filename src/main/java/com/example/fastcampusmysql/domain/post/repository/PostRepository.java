package com.example.fastcampusmysql.domain.post.repository;

import com.example.fastcampusmysql.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p where p.id in (:ids)")
    List<Post> findAllByInId(List<Long> ids);

    Page<Post> findAllByMemberId(Long memberId, Pageable pageable);

    @Query("SELECT p FROM Post p WHERE p.id = :postId ")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Post> findByIdWithPessimisticLock(Long postId);

    @Query("SELECT p FROM Post p WHERE p.id = :postId ")
    @Lock(LockModeType.OPTIMISTIC)
    Optional<Post> findByIdWithOptimisticLock(Long postId);
}
