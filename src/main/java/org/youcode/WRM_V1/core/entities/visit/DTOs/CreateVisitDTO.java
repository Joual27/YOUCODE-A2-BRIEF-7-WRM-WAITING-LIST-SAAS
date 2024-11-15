package org.youcode.WRM_V1.core.entities.visit.DTOs;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.Duration;
import java.time.LocalDateTime;

public record CreateVisitDTO(@NotNull Long visitorId ,  @NotNull Long waitingListId , @NotNull LocalDateTime arrivalTime , @Min(1) @Max(255) Integer priority , Duration estimatedVisitTime ){
}
