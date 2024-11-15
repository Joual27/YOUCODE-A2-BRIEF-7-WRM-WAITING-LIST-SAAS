package org.youcode.WRM_V1.core.entities.visit.DTOs;

import jakarta.validation.constraints.NotNull;

import java.time.Duration;
import java.time.LocalDateTime;

public record CreateVisitDTO(@NotNull Long visitorId , @NotNull Long waitingListId , @NotNull LocalDateTime arrivalTime , int priority , Duration estimatedVisitTime ){
}
