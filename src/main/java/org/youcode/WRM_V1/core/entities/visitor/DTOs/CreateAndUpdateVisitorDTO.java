package org.youcode.WRM_V1.core.entities.visitor.DTOs;

import jakarta.validation.constraints.NotNull;

public record CreateAndUpdateVisitorDTO(@NotNull String firstName , @NotNull String lastName) {
}
