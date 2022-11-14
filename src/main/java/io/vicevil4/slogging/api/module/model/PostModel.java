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
@Table(name = "TB_POST")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostModel {
  
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name = "POST_ID")
  private Long postId;

  @Column(name="POST_TITLE", length=100, nullable=false)
  private String postTitle;

  @Column(name="POST_CONTENT", columnDefinition = "LONGTEXT", nullable=false)
  private String postContent;

  @Column(name="POST_WRITER", length=100, nullable=false)
  private String postWriter;

  @Builder
  public PostModel(String postTitle, String postContent, String postWriter) {
    this.postTitle = postTitle;
    this.postContent = postContent;
    this.postWriter = postWriter;
  }
  
}
