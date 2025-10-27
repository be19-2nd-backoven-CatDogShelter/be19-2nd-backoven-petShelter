package com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity.report.commentreport;

import com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity.report.base.BasePostCommentReport;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;


@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "donationpostcommentreport")
public class DonationPostCommentReport extends BasePostCommentReport {
}
