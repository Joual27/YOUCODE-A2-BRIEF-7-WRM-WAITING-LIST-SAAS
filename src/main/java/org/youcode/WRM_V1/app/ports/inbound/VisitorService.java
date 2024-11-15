package org.youcode.WRM_V1.app.ports.inbound;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.youcode.WRM_V1.core.entities.visitor.DTOs.CreateAndUpdateVisitorDTO;
import org.youcode.WRM_V1.core.entities.visitor.DTOs.VisitorResponseDTO;

public interface VisitorService {
     VisitorResponseDTO save(CreateAndUpdateVisitorDTO data);
     VisitorResponseDTO update(CreateAndUpdateVisitorDTO data , Long id);
     Page<VisitorResponseDTO> getAllVisitors(Pageable pageable);
     VisitorResponseDTO getVisitorById(Long id);
     VisitorResponseDTO delete(Long id);
}
