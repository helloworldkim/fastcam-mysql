package com.example.fastcampusmysql.domain.post;

import com.example.fastcampusmysql.domain.post.repository.PostJdbcRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostBulkInsertTest {

    @Autowired
    private PostJdbcRepository postJdbcRepository;

    @Test
    public void bulkInsert() {
//        var easyRandom = PostFixtureFactory.get(
//                3L
//                , LocalDate.of(1970, 1, 1)
//                , LocalDate.of(2022, 2, 1)
//        );
//
//        var stopWatch = new StopWatch();
//        stopWatch.start();
//        int _1만 = 10000;
//        var posts = IntStream.range(0, _1만 * 100)
//                .parallel()
//                .mapToObj(i -> easyRandom.nextObject(Post.class))
//                .toList();
//        stopWatch.stop();
//
//        System.out.println("stopWatch.getTotalTimeSeconds() = " + stopWatch.getTotalTimeSeconds());
//        var queryWatch = new StopWatch();
//        queryWatch.start();
//        postRepository.bulkInsert(posts);
//        queryWatch.stop();
//        System.out.println("queryWatch = " + queryWatch.getTotalTimeSeconds());

//                .forEach(x ->
//                        postRepository.save(x)
//                        (x) -> System.out.println(x.getMemberId()+"/"+x.getCreatedDate() )
//                );

    }

}
