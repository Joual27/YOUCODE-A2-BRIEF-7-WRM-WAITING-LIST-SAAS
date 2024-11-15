package org.youcode.WRM_V1.core.entities.waitingList.DTOs;


import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;


public record CreateAndUpdateWaitingListDTO(@NotNull LocalDate date , String algorithm , Integer capacity){
    @AssertTrue(message = "Date must be today or in the future")
    private boolean isDate(){
        return !date.isBefore(LocalDate.now());
    }
}
