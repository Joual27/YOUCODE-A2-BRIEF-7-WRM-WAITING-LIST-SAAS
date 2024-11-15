package org.youcode.WRM_V1.app.ports.inbound;

import org.youcode.WRM_V1.core.entities.visit.DTOs.CreateVisitDTO;
import org.youcode.WRM_V1.core.entities.visit.DTOs.VisitResponseDTO;

public interface VisitService {

    VisitResponseDTO save(CreateVisitDTO data);
}
