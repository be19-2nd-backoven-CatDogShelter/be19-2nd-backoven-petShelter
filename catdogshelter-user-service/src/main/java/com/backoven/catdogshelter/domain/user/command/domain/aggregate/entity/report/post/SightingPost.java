package com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity.report.post;

import com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity.report.base.BasePost;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "sightingpost")
public class SightingPost extends BasePost {
    @Column(name = "is_blinded")
    private Boolean blinded;

    public SightingPost() {

    }
}
