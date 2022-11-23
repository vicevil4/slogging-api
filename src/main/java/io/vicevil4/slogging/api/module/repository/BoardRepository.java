package io.vicevil4.slogging.api.module.repository;

import io.vicevil4.slogging.api.module.model.BoardModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardModel, Long> {

    Optional<BoardModel> findByBoardName(String boardName);

    Page<BoardModel> findAllByBoardNameAndDelYn(String boardName, boolean isDelYn, Pageable pageable);
}
