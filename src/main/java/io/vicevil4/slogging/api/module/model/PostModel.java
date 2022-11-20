package io.vicevil4.slogging.api.module.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_POST")
@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class PostModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long postId;

    @Column(name = "POST_TITLE", length = 100, nullable = false)
    private String postTitle;

    @Column(name = "POST_CONTENT", columnDefinition = "LONGTEXT", nullable = false)
    private String postContent;

    @Column(name = "POST_WRITER", length = 100, nullable = false)
    private String postWriter;

    @Column(name = "REG_DT", columnDefinition = "DATETIME")
    @CreationTimestamp
    private LocalDateTime regDt;

    @Column(name = "UPD_DT", columnDefinition = "DATETIME")
    @UpdateTimestamp
    private LocalDateTime updDt;

    @ManyToOne
    @JoinColumn(name = "BOARD_ID")
    private BoardModel board;

    @Builder
    public PostModel(BoardModel board, String postTitle, String postContent, String postWriter) {
        this.board = board;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.postWriter = postWriter;
    }

}
