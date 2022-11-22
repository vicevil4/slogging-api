package io.vicevil4.slogging.api.module.dto;

import io.vicevil4.slogging.api.module.model.BoardModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardRequestDto {

    private Long boardId;
    private String boardName;

    @Builder
    public BoardRequestDto(String boardName) {
        this.boardName = boardName;
    }

    public BoardModel toEntity() {
        return BoardModel.builder().boardName(boardName).build();
    }
}
