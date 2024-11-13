package org.youcode.WRM.core.entities.waitingList.DTOs;


import org.youcode.WRM.core.entities.visit.DTOs.EmbeddedVisitDTO;

import java.time.LocalDate;
import java.util.List;

public record WaitingListResponseDTO(Long id , LocalDate date , String algorithm , int capacity  , List<EmbeddedVisitDTO> visits) {
}
