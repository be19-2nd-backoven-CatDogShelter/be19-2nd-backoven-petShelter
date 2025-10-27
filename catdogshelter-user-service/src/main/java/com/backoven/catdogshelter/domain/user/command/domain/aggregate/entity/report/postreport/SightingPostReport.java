package com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity.report.postreport;

import com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity.report.base.BasePostReport;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "sightingpostreport")
public class SightingPostReport extends BasePostReport {
}
