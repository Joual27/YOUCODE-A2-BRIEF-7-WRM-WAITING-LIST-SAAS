package org.youcode.WRM_V1.core.entities.visitor.mappers;

import org.mapstruct.Mapper;
import org.youcode.WRM_V1.common.interfaces.BaseMapper;
import org.youcode.WRM_V1.core.entities.visitor.DTOs.EmbeddedVisitorDTO;
import org.youcode.WRM_V1.core.entities.visitor.Visitor;

@Mapper(componentModel = "spring")
public interface VisitorEntityToEmbeddedVisitorDTOMapper extends BaseMapper<Visitor , EmbeddedVisitorDTO> {
}
