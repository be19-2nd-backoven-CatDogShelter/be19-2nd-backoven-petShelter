package com.backoven.catdogshelter.domain.user.command.domain.repository.report.comment;

import com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity.report.comment.AdoptionPostComment;
import com.backoven.catdogshelter.domain.user.command.domain.repository.report.base.BasePostCommentRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdoptionPostCommentRepository extends BasePostCommentRepository<AdoptionPostComment> {
}
