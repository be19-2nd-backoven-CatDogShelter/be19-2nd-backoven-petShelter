package com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity.report.post;

import com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity.report.base.BasePost;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "adoptionpost")
public class AdoptionPost extends BasePost {
    @Column(name = "is_blind")
    private Boolean blinded;

    public AdoptionPost() {

    }
}
