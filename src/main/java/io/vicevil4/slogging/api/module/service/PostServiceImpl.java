package io.vicevil4.slogging.api.module.service;

import io.vicevil4.slogging.api.module.dto.BoardRequestDto;
import io.vicevil4.slogging.api.module.dto.BoardResponseDto;
import io.vicevil4.slogging.api.module.model.BoardModel;
import io.vicevil4.slogging.api.module.model.PostModel;
import io.vicevil4.slogging.api.module.repository.BoardRepository;
import io.vicevil4.slogging.api.module.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Transactional
    @Override
    public BoardResponseDto.Post createPost(Long boardId, BoardRequestDto.CreatePost postDto) {

        PostModel post = postRepository.save(postDto.toEntity(boardId));
        
        return BoardResponseDto.Post.builder()
                .postId(post.getPostId())
                .postTitle(post.getPostTitle())
                .postContent(post.getPostContent())
                .postWriter(post.getPostWriter())
                .board(BoardResponseDto.Board.fromEntity(post.getBoard()))
                .build();
    }
}
