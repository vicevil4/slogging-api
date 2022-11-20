package io.vicevil4.slogging.api.module.repository;

import io.vicevil4.slogging.api.module.model.BoardModel;
import io.vicevil4.slogging.api.module.model.PostModel;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Slf4j
public class PostRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    PostRepository postRepository;

    BoardModel prepareBoard(String boardName) {
        BoardModel board = BoardModel.builder().boardName(boardName).build();
        return boardRepository.save(board);
    }

    @Test
    void save() {
        // given
        BoardModel savedBoard = prepareBoard(RandomString.make(5));
        String postWriter = "홍길동";
        PostModel post = PostModel.builder().postContent("내용입니다")
                .postTitle("제목이에요")
                .postWriter(postWriter)
                .board(savedBoard).build();

        // when
        PostModel savedPost = postRepository.save(post);
        log.info("savedPost {}", savedPost);

        // then
        Assertions.assertEquals(postWriter, savedPost.getPostWriter());
    }

    @Test
    void findAll() {
        // given
        BoardModel savedBoard = prepareBoard(RandomString.make(5));
        final int postCount = 20;
        for (int ii = 0; ii < postCount; ii++) {
            PostModel post = PostModel.builder().postContent(RandomString.make(100))
                    .postTitle(RandomString.make(100))
                    .postWriter("홍길동")
                    .board(savedBoard).build();
            postRepository.save(post);
        }

        // when
        List<PostModel> postList = postRepository.findAll();
        log.info("postList {}", postList);

        // then
        Assertions.assertEquals(postCount, postList.size());
    }
}
