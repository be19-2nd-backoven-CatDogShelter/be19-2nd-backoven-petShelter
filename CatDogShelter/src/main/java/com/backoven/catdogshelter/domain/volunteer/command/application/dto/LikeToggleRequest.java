package com.backoven.catdogshelter.domain.volunteer.command.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class LikeToggleRequest {
    public enum ActorType { USER, HEAD }
    private ActorType actorType;
    private Integer userId; // USER면 필수
    private Integer headId; // HEAD면 필수
}