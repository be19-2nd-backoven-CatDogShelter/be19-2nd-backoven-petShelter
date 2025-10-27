package com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity.report.base;

import com.backoven.catdogshelter.common.util.ReportCategory;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@MappedSuperclass
public class BasePostCommentReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private ReportCategory category;

    @Column(name = "etc_detail")
    private String etcDetail;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "status")
    private boolean status;

    @Column(name = "comment_id")
    private Integer commentId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "head_id")
    private Integer headId;
}
