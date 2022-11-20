package io.vicevil4.slogging.api.module.model;

import io.vicevil4.slogging.api.module.model.converter.BooleanYnConverter;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_BOARD")
@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class BoardModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_ID")
    private Long boardId;

    @Column(name = "BOARD_NAME", length = 100, nullable = false)
    private String boardName;

    @Convert(converter = BooleanYnConverter.class)
    @Column(name = "DEL_YN", length = 1, nullable = false
            , columnDefinition = "CHAR(1) DEFAULT 'N'")
    private boolean delYn;

    @Column(name = "REG_DT", columnDefinition = "DATETIME")
    @CreationTimestamp
    private LocalDateTime regDt;

    @Column(name = "UPD_DT", columnDefinition = "DATETIME")
    @UpdateTimestamp
    private LocalDateTime updDt;

    @Builder
    public BoardModel(String boardName, boolean delYn) {

        this.boardName = boardName;
        this.delYn = delYn;
    }

}
