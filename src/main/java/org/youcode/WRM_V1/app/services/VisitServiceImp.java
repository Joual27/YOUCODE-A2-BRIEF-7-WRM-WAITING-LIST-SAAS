package org.youcode.WRM_V1.app.services;

import org.springframework.stereotype.Service;
import org.youcode.WRM_V1.app.ports.inbound.VisitService;
import org.youcode.WRM_V1.core.entities.visit.DTOs.CreateVisitDTO;
import org.youcode.WRM_V1.core.entities.visit.DTOs.VisitResponseDTO;
import org.youcode.WRM_V1.core.entities.visit.mappers.CreateVisitDTOToVisitEntityMapper;
import org.youcode.WRM_V1.core.entities.visit.mappers.VisitEntityToVisitResponseDTOMapper;
import org.youcode.WRM_V1.infra.adapters.outbound.persistence.VisitPersistenceAdapter;

@Service
public class VisitServiceImp implements VisitService {
    private final VisitPersistenceAdapter visitPersistenceAdapter;
    private final CreateVisitDTOToVisitEntityMapper createVisitDTOToVisitEntityMapper;
    private final VisitEntityToVisitResponseDTOMapper visitEntityToVisitResponseDTOMapper;

    public VisitServiceImp (VisitPersistenceAdapter visitPersistenceAdapter , CreateVisitDTOToVisitEntityMapper createVisitDTOToVisitEntityMapper , VisitEntityToVisitResponseDTOMapper visitEntityToVisitResponseDTOMapper){
        this.createVisitDTOToVisitEntityMapper = createVisitDTOToVisitEntityMapper;
        this.visitEntityToVisitResponseDTOMapper = visitEntityToVisitResponseDTOMapper;
        this.visitPersistenceAdapter = visitPersistenceAdapter;
    }

    @Override
    public VisitResponseDTO save(CreateVisitDTO data){

    }
}
