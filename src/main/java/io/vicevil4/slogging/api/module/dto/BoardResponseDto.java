package io.vicevil4.slogging.api.module.dto;

import io.vicevil4.slogging.api.module.model.BoardModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardResponseDto {

    private Long boardId;
    private String boardName;

    @Builder
    public BoardResponseDto(String boardName) {
        this.boardName = boardName;
    }

}
