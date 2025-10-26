package com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity.report.comment;

import com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity.report.base.BasePostComment;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "sightingpostcomment")
public class SightingPostComment extends BasePostComment {
}
