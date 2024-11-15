package org.youcode.WRM_V1.core.entities.waitingList.mappers;

import org.mapstruct.Mapper;
import org.youcode.WRM_V1.common.interfaces.BaseMapper;
import org.youcode.WRM_V1.core.entities.visit.mappers.VisitEntityToEmbeddedVisitDTOMapper;
import org.youcode.WRM_V1.core.entities.waitingList.DTOs.WaitingListResponseDTO;
import org.youcode.WRM_V1.core.entities.waitingList.WaitingList;

@Mapper(componentModel = "spring" , uses = VisitEntityToEmbeddedVisitDTOMapper.class)
public interface WaitingListEntityToWaitingListResponseDTOMapper extends BaseMapper<WaitingList , WaitingListResponseDTO> {
}
