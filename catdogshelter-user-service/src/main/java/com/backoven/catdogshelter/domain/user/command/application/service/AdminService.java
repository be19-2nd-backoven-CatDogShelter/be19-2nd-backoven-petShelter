package com.backoven.catdogshelter.domain.user.command.application.service;

public interface AdminService {
    void PostReport(String category, int postId, boolean blind);

    void CommentReport(String category, int commentId, boolean blind);

    void promoteToAdmin(Integer userId);
}
