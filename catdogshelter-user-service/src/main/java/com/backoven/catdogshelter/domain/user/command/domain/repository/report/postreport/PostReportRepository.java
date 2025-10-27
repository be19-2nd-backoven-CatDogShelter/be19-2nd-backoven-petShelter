package com.backoven.catdogshelter.domain.user.command.domain.repository.report.postreport;

import com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity.report.postreport.PostReport;
import com.backoven.catdogshelter.domain.user.command.domain.repository.report.base.BasePostReportRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostReportRepository extends BasePostReportRepository<PostReport> {
}
