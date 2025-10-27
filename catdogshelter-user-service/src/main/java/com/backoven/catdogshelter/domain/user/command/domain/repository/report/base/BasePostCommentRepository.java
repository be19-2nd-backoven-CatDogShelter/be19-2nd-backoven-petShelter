package com.backoven.catdogshelter.domain.user.command.domain.repository.report.base;

import com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity.report.base.BasePostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BasePostCommentRepository<T extends BasePostComment>
        extends JpaRepository<T, Integer> {

}