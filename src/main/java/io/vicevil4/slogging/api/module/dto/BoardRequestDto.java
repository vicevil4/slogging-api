package io.vicevil4.slogging.api.module.dto;

import io.vicevil4.slogging.api.module.model.BoardModel;
import lombok.*;

import javax.validation.constraints.NotNull;

public class BoardRequestDto {

    @Getter
    @Setter
    @ToString
    public static class GetBoards {

        private String boardName;

    }

    @Getter
    @Setter
    @ToString
    public static class CreateBoard {

        @NotNull
        private String boardName;

        public BoardModel toEntity() {
            return BoardModel.builder().boardName(boardName).build();
        }
    }


}
