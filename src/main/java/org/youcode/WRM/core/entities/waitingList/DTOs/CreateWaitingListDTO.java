package org.youcode.WRM.core.entities.waitingList.DTOs;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;


public record CreateWaitingListDTO (@NotNull LocalDate date , String algorithm , int capacity){
}
