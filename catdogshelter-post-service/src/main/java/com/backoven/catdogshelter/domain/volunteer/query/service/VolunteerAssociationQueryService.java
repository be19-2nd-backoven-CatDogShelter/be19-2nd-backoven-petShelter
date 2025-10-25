package com.backoven.catdogshelter.domain.volunteer.query.service;

import com.backoven.catdogshelter.domain.volunteer.query.dto.VolunteerAssociationDTO;
import com.backoven.catdogshelter.domain.volunteer.query.dto.VolunteerAssociationQueryDTO;
import com.backoven.catdogshelter.domain.volunteer.query.dto.VolunteerAssociationSearchCond;

import java.util.List;

public interface VolunteerAssociationQueryService {

    List<VolunteerAssociationDTO> getHeadsAssociations(Integer headId);

    VolunteerAssociationQueryDTO selectVolunteerAssociation(Integer id);

    List<VolunteerAssociationQueryDTO> selectVolunteerAssociationsBySearch(VolunteerAssociationSearchCond cond);
}
