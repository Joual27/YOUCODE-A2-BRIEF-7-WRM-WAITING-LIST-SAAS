package org.youcode.WRM.core.entities.visit.DTOs;

import org.youcode.WRM.core.entities.visitor.DTOs.EmbeddedVisitorDTO;
import org.youcode.WRM.core.entities.waitingList.DTOs.EmbeddedWaitingListDTO;

import java.time.LocalDateTime;

public record EmbeddedVisitDTO(LocalDateTime startDate , LocalDateTime endDate , EmbeddedVisitorDTO visitor , EmbeddedWaitingListDTO waitingList) {
}
