package com.backoven.catdogshelter.domain.user.command.domain.repository.report.postreport;

import com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity.report.postreport.SightingPostReport;
import com.backoven.catdogshelter.domain.user.command.domain.repository.report.base.BasePostReportRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SightingPostReportRepository extends BasePostReportRepository<SightingPostReport> {
}
