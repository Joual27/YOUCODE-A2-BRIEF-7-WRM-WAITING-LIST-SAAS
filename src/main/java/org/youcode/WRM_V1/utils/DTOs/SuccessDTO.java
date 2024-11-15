package org.youcode.WRM_V1.utils.DTOs;

public record SuccessDTO<T>(String status , String message , T data) {
}
