package org.youcode.WRM_V1.core.entities.waitingList.DTOs;


import com.fasterxml.jackson.annotation.JsonInclude;
import org.youcode.WRM_V1.core.entities.visit.DTOs.EmbeddedVisitDTO;

import java.time.LocalDate;
import java.util.List;

public record WaitingListResponseDTO(Long id , LocalDate date , String algorithm , int capacity  , @JsonInclude(JsonInclude.Include.NON_NULL) List<EmbeddedVisitDTO> visits) {
}
