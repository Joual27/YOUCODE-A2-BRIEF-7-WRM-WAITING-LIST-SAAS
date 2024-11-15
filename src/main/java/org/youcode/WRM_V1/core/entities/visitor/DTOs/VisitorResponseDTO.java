package org.youcode.WRM_V1.core.entities.visitor.DTOs;

import org.youcode.WRM_V1.core.entities.visit.DTOs.EmbeddedVisitDTO;

import java.util.List;

public record VisitorResponseDTO(String firstName , String lastName , List<EmbeddedVisitDTO> visits) {
}
