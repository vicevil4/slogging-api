package io.vicevil4.slogging.api.module.model;

import io.vicevil4.slogging.api.module.model.base.BaseModel;
import io.vicevil4.slogging.api.module.model.converter.BooleanYnConverter;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TB_BOARD"
        , uniqueConstraints = {
        @UniqueConstraint(columnNames = {"BOARD_NAME"})
}
)
@DynamicInsert
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class BoardModel extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_ID")
    private Long boardId;

    @Column(name = "BOARD_NAME", length = 100, nullable = false)
    @NotNull
    private String boardName;

    @Convert(converter = BooleanYnConverter.class)
    @Column(name = "DEL_YN", length = 1, nullable = false
            , columnDefinition = "CHAR(1) DEFAULT 'N'")
    @NotNull
    private boolean delYn;

    @Builder
    public BoardModel(String boardName, boolean delYn) {

        this.boardName = boardName;
        this.delYn = delYn;
    }

}
