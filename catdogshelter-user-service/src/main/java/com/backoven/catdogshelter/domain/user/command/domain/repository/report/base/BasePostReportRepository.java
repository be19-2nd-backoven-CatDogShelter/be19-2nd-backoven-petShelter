package com.backoven.catdogshelter.domain.user.command.domain.repository.report.base;

import com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity.report.base.BasePostReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BasePostReportRepository<T extends BasePostReport>
        extends JpaRepository<T, Integer> {

    List<T> findByPostId(Integer postId);
}