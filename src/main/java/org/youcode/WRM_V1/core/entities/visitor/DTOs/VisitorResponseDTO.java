package org.youcode.WRM_V1.core.entities.visitor.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.youcode.WRM_V1.core.entities.visit.DTOs.EmbeddedVisitDTO;

import java.util.List;

public record VisitorResponseDTO(Long id, String firstName , String lastName , @JsonInclude(JsonInclude.Include.NON_NULL) List<EmbeddedVisitDTO> visits) {
}
