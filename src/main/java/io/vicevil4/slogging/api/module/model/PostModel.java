package io.vicevil4.slogging.api.module.model;

import io.vicevil4.slogging.api.module.model.base.BaseModel;
import io.vicevil4.slogging.api.module.model.converter.BooleanYnConverter;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_POST")
@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class PostModel extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long postId;

    @Column(name = "POST_TITLE", length = 100, nullable = false)
    @NotNull
    private String postTitle;

    @Column(name = "POST_CONTENT", columnDefinition = "LONGTEXT", nullable = false)
    @NotNull
    private String postContent;

    @Column(name = "POST_WRITER", length = 100, nullable = false)
    @NotNull
    private String postWriter;

    @Convert(converter = BooleanYnConverter.class)
    @Column(name = "DEL_YN", length = 1, nullable = false
            , columnDefinition = "CHAR(1) DEFAULT 'N'")
    @NotNull
    private boolean delYn;

    @ManyToOne
    @JoinColumn(name = "BOARD_ID")
    private BoardModel board;

    @Builder
    public PostModel(BoardModel board, String postTitle, String postContent, String postWriter, boolean delYn) {
        this.board = board;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.postWriter = postWriter;
        this.delYn = delYn;
    }

}
