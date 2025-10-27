package com.backoven.catdogshelter.domain.user.command.domain.repository.report.commentreport;

import com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity.report.commentreport.MissingPostCommentReport;
import com.backoven.catdogshelter.domain.user.command.domain.repository.report.base.BasePostCommentReportRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissingPostCommentReportRepository extends BasePostCommentReportRepository<MissingPostCommentReport> {
}
