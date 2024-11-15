package org.youcode.WRM_V1.core.entities.waitingList.DTOs;


import jakarta.validation.constraints.NotNull;
import org.youcode.WRM_V1.utils.validators.interfaces.DateTodayOrFuture;

import java.time.LocalDate;


public record CreateAndUpdateWaitingListDTO(@NotNull @DateTodayOrFuture LocalDate date , String algorithm , Integer capacity){
}
