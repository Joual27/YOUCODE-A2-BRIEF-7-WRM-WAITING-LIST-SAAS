package org.youcode.WRM_V1.core.entities.visitor.mappers;


import org.mapstruct.Mapper;
import org.youcode.WRM_V1.common.interfaces.BaseMapper;
import org.youcode.WRM_V1.core.entities.visit.mappers.VisitEntityToEmbeddedVisitDTOMapper;
import org.youcode.WRM_V1.core.entities.visitor.DTOs.VisitorResponseDTO;
import org.youcode.WRM_V1.core.entities.visitor.Visitor;

@Mapper(componentModel = "spring" , uses = VisitEntityToEmbeddedVisitDTOMapper.class)
public interface VisitorEntityToVisitorResponseDTOMapper extends BaseMapper<Visitor , VisitorResponseDTO> {
}
