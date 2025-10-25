package com.backoven.catdogshelter.domain.volunteer.command.application.dto;

import lombok.Data;

@Data
public class VolunteerAssociationFileDTO {
    private Integer id;
    private String fileRename;
    private String filePath;
    private String uploadedAt;
}
