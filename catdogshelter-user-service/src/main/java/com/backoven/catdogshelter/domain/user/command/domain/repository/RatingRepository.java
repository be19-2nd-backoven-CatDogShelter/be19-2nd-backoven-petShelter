package com.backoven.catdogshelter.domain.user.command.domain.repository;

import com.backoven.catdogshelter.common.entity.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<RatingEntity, Integer> {
}
