package com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity.report.base;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@MappedSuperclass
public class BasePost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    @Column(name = "view")
    private Integer view;

    @Column(name = "is_deleted")
    private Boolean deleted;
}
