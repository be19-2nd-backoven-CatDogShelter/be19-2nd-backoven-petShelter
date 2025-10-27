package com.backoven.catdogshelter.domain.user.command.domain.repository.report.commentreport;

import com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity.report.commentreport.DonationPostCommentReport;
import com.backoven.catdogshelter.domain.user.command.domain.repository.report.base.BasePostCommentReportRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationPostCommentReportRepository extends BasePostCommentReportRepository<DonationPostCommentReport> {
}
