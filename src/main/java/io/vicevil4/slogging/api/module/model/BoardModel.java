package io.vicevil4.slogging.api.module.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_BOARD")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardModel {
  
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name = "BOARD_ID")
  private Long boardId;

  @Column(name="BOARD_NAME", length=100, nullable=false)
  private String boardName;

  @Builder
  public BoardModel(String boardName) {
    this.boardName = boardName;
  }
}
