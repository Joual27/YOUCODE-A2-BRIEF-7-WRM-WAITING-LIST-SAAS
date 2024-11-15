package org.youcode.WRM_V1.core.entities.waitingList.DTOs;


import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;


public record CreateWaitingListDTO (@NotNull LocalDate date , String algorithm , Integer capacity){
    public CreateWaitingListDTO {
        assert !date.isBefore(LocalDate.now()) : "Date must be today or in the future";
    }
}
