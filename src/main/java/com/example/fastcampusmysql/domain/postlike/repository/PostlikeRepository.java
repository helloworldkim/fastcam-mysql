package com.example.fastcampusmysql.domain.postlike.repository;


import com.example.fastcampusmysql.domain.postlike.entity.Postlike;
import com.example.fastcampusmysql.domain.timeline.entity.Timeline;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostlikeRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    static final private String TABLE = "Postlike";

    static final RowMapper<Timeline> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> Timeline.builder()
                .id(resultSet.getLong("id"))
                .memberId(resultSet.getLong("memberId"))
                .postId(resultSet.getLong("postId"))
                .createdAt(resultSet.getObject("createdAt", LocalDateTime.class))
                .build();

    public Postlike save(Postlike postlike) {

        if (postlike.getId() == null) {
            return insert(postlike);
        }
        throw new UnsupportedOperationException("Postlike는 갱신을 지원하지 않습니다.");

    }

    public Long count(Long postId) {
        var sql = String.format("""
                SELECT COUNT(id)
                FROM %s
                WHERE postId = :postId
                """, TABLE);
        var params = new MapSqlParameterSource().addValue("postId", postId);
        return namedParameterJdbcTemplate.queryForObject(sql, params, Long.class);
    }


    private Postlike insert(Postlike postlike) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(namedParameterJdbcTemplate.getJdbcTemplate())
                .withTableName(TABLE)
                .usingGeneratedKeyColumns("id");
        SqlParameterSource params = new BeanPropertySqlParameterSource(postlike);

        var id  = simpleJdbcInsert.executeAndReturnKey(params).longValue();

        return Postlike.builder()
                .id(id)
                .memberId(postlike.getMemberId())
                .postId(postlike.getPostId())
                .createdAt(postlike.getCreatedAt())
                .build();

    }



}
