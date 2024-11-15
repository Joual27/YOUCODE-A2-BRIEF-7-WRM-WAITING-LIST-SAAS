package org.youcode.WRM_V1.common.interfaces;

public interface BaseMapper <T , DTO>{
    T toEntity(DTO dto);
    DTO entityToDto(T entity);
}
