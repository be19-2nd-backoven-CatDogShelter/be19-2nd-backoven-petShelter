package com.backoven.catdogshelter.domain.volunteer.query.dto;

import lombok.Data;

@Data
public class VolunteerAssociationDTO {
    private Integer id;
    private String title;
    private String content;
    private String createdAt;
    private int time;
    private String detailAddress;
    private boolean deadline;
    private int numberOfPeople;
    private boolean isEnd;
    private String headName;
    private String headAddress;
}
