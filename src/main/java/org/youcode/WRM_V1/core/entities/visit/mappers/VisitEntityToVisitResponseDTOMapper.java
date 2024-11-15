package org.youcode.WRM_V1.core.entities.visit.mappers;

import org.mapstruct.Mapper;
import org.youcode.WRM_V1.common.interfaces.BaseMapper;
import org.youcode.WRM_V1.core.entities.visit.DTOs.VisitResponseDTO;
import org.youcode.WRM_V1.core.entities.visit.Visit;
import org.youcode.WRM_V1.core.entities.visitor.mappers.VisitorEntityToEmbeddedVisitorDTOMapper;
import org.youcode.WRM_V1.core.entities.waitingList.mappers.WaitingListEntityToEmbeddedWaitingListDTOMapper;

@Mapper(componentModel = "spring" , uses = {VisitorEntityToEmbeddedVisitorDTOMapper.class , WaitingListEntityToEmbeddedWaitingListDTOMapper.class})
public interface VisitEntityToVisitResponseDTOMapper extends BaseMapper<Visit , VisitResponseDTO> {
}
