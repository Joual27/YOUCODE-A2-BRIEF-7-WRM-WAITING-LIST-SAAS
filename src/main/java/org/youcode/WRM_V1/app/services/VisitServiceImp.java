package org.youcode.WRM_V1.app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.youcode.WRM_V1.app.ports.inbound.VisitService;
import org.youcode.WRM_V1.core.entities.visit.DTOs.CreateVisitDTO;
import org.youcode.WRM_V1.core.entities.visit.DTOs.VisitResponseDTO;
import org.youcode.WRM_V1.core.entities.visit.Visit;
import org.youcode.WRM_V1.core.entities.visit.mappers.CreateVisitDTOToVisitEntityMapper;
import org.youcode.WRM_V1.core.entities.visit.mappers.VisitEntityToVisitResponseDTOMapper;
import org.youcode.WRM_V1.core.entities.visitor.Visitor;
import org.youcode.WRM_V1.core.entities.waitingList.WaitingList;
import org.youcode.WRM_V1.core.exceptions.EntityNotFoundException;
import org.youcode.WRM_V1.core.exceptions.InvalidArrivalTimeException;
import org.youcode.WRM_V1.core.exceptions.MissingDataException;
import org.youcode.WRM_V1.infra.adapters.outbound.persistence.VisitPersistenceAdapter;
import org.youcode.WRM_V1.infra.adapters.outbound.persistence.VisitorPersistenceAdapter;
import org.youcode.WRM_V1.infra.adapters.outbound.persistence.WaitingListPersistenceAdapter;

import java.time.LocalDateTime;

@Service
public class VisitServiceImp implements VisitService {
    private final VisitPersistenceAdapter visitPersistenceAdapter;
    private final WaitingListPersistenceAdapter waitingListPersistenceAdapter;
    private final VisitorPersistenceAdapter visitorPersistenceAdapter;
    private final CreateVisitDTOToVisitEntityMapper createVisitDTOToVisitEntityMapper;
    private final VisitEntityToVisitResponseDTOMapper visitEntityToVisitResponseDTOMapper;

    public VisitServiceImp (VisitPersistenceAdapter visitPersistenceAdapter , WaitingListPersistenceAdapter waitingListPersistenceAdapter, VisitorPersistenceAdapter visitorPersistenceAdapter ,CreateVisitDTOToVisitEntityMapper createVisitDTOToVisitEntityMapper , VisitEntityToVisitResponseDTOMapper visitEntityToVisitResponseDTOMapper){
        this.createVisitDTOToVisitEntityMapper = createVisitDTOToVisitEntityMapper;
        this.visitEntityToVisitResponseDTOMapper = visitEntityToVisitResponseDTOMapper;
        this.visitPersistenceAdapter = visitPersistenceAdapter;
        this.visitorPersistenceAdapter = visitorPersistenceAdapter;
        this.waitingListPersistenceAdapter = waitingListPersistenceAdapter;
    }

    @Override
    public VisitResponseDTO save(CreateVisitDTO data){
        Visitor v = visitorPersistenceAdapter.findById(data.visitorId())
                .orElseThrow(() -> new EntityNotFoundException("No such visitor with given ID !"));
        WaitingList w = waitingListPersistenceAdapter.findById(data.waitingListId())
                .orElseThrow(() -> new EntityNotFoundException("No such waiting list with given ID !"));
        if (data.arrivalTime().isBefore(LocalDateTime.now())){
            throw new InvalidArrivalTimeException("Arrival Time can't be before now");
        }
        handleAlgorithmDataValidation(w.getAlgorithm() , data);
        Visit visitToCreate = createVisitDTOToVisitEntityMapper.toEntity(data);
        Visit createdVisit = visitPersistenceAdapter.save(visitToCreate);
        createdVisit.setVisitor(v);
        createdVisit.setWaitingList(w);
        return visitEntityToVisitResponseDTOMapper.entityToDto(createdVisit);
    }


    @Override
    public Page<VisitResponseDTO> getAllVisits(Pageable pageable){
        Page<Visit> visits = visitPersistenceAdapter.findAll(pageable);
        return visits.map(visitEntityToVisitResponseDTOMapper::entityToDto);
    }

    private void handleAlgorithmDataValidation(String algorithm , CreateVisitDTO data){
        switch (algorithm){
            case "PF" :
                if (data.priority() == null){
                    throw new MissingDataException("To Apply the Priority First algorithm , it's mandatory to have The priority value !");
                }
                break;
            case "SJF":
                if (data.estimatedVisitTime() == null){
                    throw new MissingDataException("To Apply the Priority First algorithm , it's mandatory to have The priority value !");
                }
                break;
        }
    }
}
