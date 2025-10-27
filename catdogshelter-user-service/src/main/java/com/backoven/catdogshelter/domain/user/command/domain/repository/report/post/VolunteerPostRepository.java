package com.backoven.catdogshelter.domain.user.command.domain.repository.report.post;

import com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity.report.post.VolunteerPost;
import com.backoven.catdogshelter.domain.user.command.domain.repository.report.base.BasePostRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerPostRepository extends BasePostRepository<VolunteerPost> {
}
