package com.backoven.catdogshelter.domain.user.command.domain.repository.report.base;

import com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity.report.base.BasePostCommentReport;
import com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity.report.commentreport.AdoptionPostCommentReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BasePostCommentReportRepository<T extends BasePostCommentReport>
        extends JpaRepository<T, Integer> {
    List<T> findByCommentId(int commentId);
}