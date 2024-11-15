package org.youcode.WRM_V1.core.entities.visit.DTOs;

import org.youcode.WRM_V1.core.entities.embeddables.VisitKey;
import org.youcode.WRM_V1.core.entities.visitor.DTOs.EmbeddedVisitorDTO;
import org.youcode.WRM_V1.core.entities.waitingList.DTOs.EmbeddedWaitingListDTO;

import java.time.Duration;
import java.time.LocalDateTime;

public record VisitResponseDTO(VisitKey id , LocalDateTime arrivalTime , LocalDateTime endDate , String status , int priority , Duration estimatedVisitTime , EmbeddedVisitorDTO visitor , EmbeddedWaitingListDTO waitingList) {
}
