package io.vicevil4.slogging.api.module.model.base;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@ToString
public abstract class BaseModel {

    @Column(name = "REG_DT", columnDefinition = "DATETIME")
    @CreationTimestamp
    private LocalDateTime regDt;

    @Column(name = "UPD_DT", columnDefinition = "DATETIME")
    @UpdateTimestamp
    private LocalDateTime updDt;
}
