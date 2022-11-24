package io.vicevil4.slogging.api.module.repository;

import io.vicevil4.slogging.api.module.model.BoardModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardModel, Long> {

    Optional<BoardModel> findByBoardName(String boardName);

    Page<BoardModel> findAllByBoardNameStartsWithAndDelYn(String boardName, boolean isDelYn, Pageable pageable);

    @Modifying
    @Query(value = "UPDATE TB_BOARD B SET B.DEL_YN = 'Y', B.UPD_DT = CURRENT_TIMESTAMP WHERE B.BOARD_ID = :boardId", nativeQuery = true)
    int deleteBoard(@Param(value = "boardId") long id);
}
