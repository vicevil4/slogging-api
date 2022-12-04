package io.vicevil4.slogging.api.module.service;

import io.vicevil4.slogging.api.module.dto.BoardRequestDto;
import io.vicevil4.slogging.api.module.dto.BoardResponseDto;
import org.springframework.data.domain.Pageable;

public interface PostService {

    BoardResponseDto.Post createPost(Long boardId, BoardRequestDto.CreatePost postDto);
}
