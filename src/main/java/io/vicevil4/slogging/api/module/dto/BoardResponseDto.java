package io.vicevil4.slogging.api.module.dto;

import io.vicevil4.slogging.api.module.model.BoardModel;
import io.vicevil4.slogging.api.module.model.PostModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

public class BoardResponseDto {

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class BoardList {
        private Page<Board> list;
    }

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Board {
        private Long boardId;
        private String boardName;

        public static Board fromEntity(BoardModel b) {
            return Board.builder().boardId(b.getBoardId()).boardName(b.getBoardName()).build();
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Post {
        private Long postId;
        private String postTitle;
        private String postContent;
        private String postWriter;
        private Board board;

        public static Post fromEntity(PostModel p) {
            return Post.builder().postId(p.getPostId())
                    .postTitle(p.getPostTitle())
                    .postContent(p.getPostContent())
                    .postWriter(p.getPostWriter())
                    .board(Board.fromEntity(p.getBoard()))
                    .build();
        }
    }
}
