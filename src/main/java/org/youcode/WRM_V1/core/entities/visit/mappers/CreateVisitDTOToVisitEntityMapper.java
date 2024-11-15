package org.youcode.WRM_V1.core.entities.visit.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.youcode.WRM_V1.common.interfaces.BaseMapper;
import org.youcode.WRM_V1.core.entities.visit.DTOs.CreateVisitDTO;
import org.youcode.WRM_V1.core.entities.visit.Visit;

@Mapper(componentModel = "spring")
public interface CreateVisitDTOToVisitEntityMapper extends BaseMapper<Visit , CreateVisitDTO> {
    @Mapping(target = "id.visitorId" , source = "visitorId")
    @Mapping(target = "id.waitingListId" , source = "waitingListId")
    @Mapping(target = "waitingList.id" , source = "waitingListId")
    @Mapping(target = "visitor.id" , source = "visitorId" )
    Visit toEntity(CreateVisitDTO dto);
}
