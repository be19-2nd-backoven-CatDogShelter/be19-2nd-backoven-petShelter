package com.backoven.catdogshelter.domain.post.command.domain.aggregate.entity;


import com.backoven.catdogshelter.common.util.DateTimeUtil;
import com.backoven.catdogshelter.common.util.ReportCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="postcommentreport")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostCommentReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private ReportCategory category;

    @Column(name="etc_detail")
    private String etcDetail;

    @Column(name = "created_at", nullable = false)
    private String createdAt;

    @Column(name="comment_id")
    private int commentId;

    @Column(name="user_id")
    private Integer userId;

    @Column(name="head_id")
    private Integer headId;

    // 입력할때 현재 서버 시간을 계산하기 위해서 util 클래스 아래 DatTimeUtil 클래스 안에 공통된 기능 개발
    @PrePersist
    public void prePersist() {
        this.createdAt = DateTimeUtil.now();
    }
}
