package com.backoven.catdogshelter.domain.sighting.command.domain.repository;

import com.backoven.catdogshelter.domain.sighting.command.domain.aggregate.entity.SightingPostLiked;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SightingPostLikedRepository extends JpaRepository<SightingPostLiked, Integer> {
    Optional<SightingPostLiked> findByPostIdAndUserId(int postId, Integer userId);

    Optional<SightingPostLiked> findByPostIdAndHeadId(int postId, Integer headId);
}
