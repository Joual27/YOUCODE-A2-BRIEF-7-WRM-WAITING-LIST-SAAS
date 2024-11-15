package org.youcode.WRM_V1.core.entities.waitingList.mappers;

import org.mapstruct.Mapper;
import org.youcode.WRM_V1.common.interfaces.BaseMapper;
import org.youcode.WRM_V1.core.entities.waitingList.DTOs.CreateWaitingListDTO;
import org.youcode.WRM_V1.core.entities.waitingList.WaitingList;

@Mapper(componentModel = "spring")
public interface CreateWaitingListDTOToWaitingListEntityMapper extends BaseMapper<WaitingList , CreateWaitingListDTO> {

}
