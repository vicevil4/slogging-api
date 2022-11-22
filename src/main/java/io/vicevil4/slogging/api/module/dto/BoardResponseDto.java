package io.vicevil4.slogging.api.module.dto;

import lombok.*;

public class BoardResponseDto {

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CreateBoard {
        private Long boardId;
        private String boardName;
    }

}
