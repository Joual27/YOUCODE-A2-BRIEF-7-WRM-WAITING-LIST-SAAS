package org.youcode.WRM_V1.core.entities.waitingList.mappers;

import org.mapstruct.Mapper;
import org.youcode.WRM_V1.common.interfaces.BaseMapper;
import org.youcode.WRM_V1.core.entities.waitingList.DTOs.EmbeddedWaitingListDTO;
import org.youcode.WRM_V1.core.entities.waitingList.WaitingList;

@Mapper(componentModel = "spring")
public interface WaitingListEntityToEmbeddedWaitingListDTOMapper extends BaseMapper<WaitingList , EmbeddedWaitingListDTO> {
}
