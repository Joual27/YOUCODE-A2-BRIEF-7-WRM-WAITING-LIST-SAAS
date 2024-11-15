package org.youcode.WRM_V1.app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.youcode.WRM_V1.app.ports.inbound.VisitorService;
import org.youcode.WRM_V1.core.entities.visit.Visit;
import org.youcode.WRM_V1.core.entities.visitor.DTOs.CreateAndUpdateVisitorDTO;
import org.youcode.WRM_V1.core.entities.visitor.DTOs.VisitorResponseDTO;
import org.youcode.WRM_V1.core.entities.visitor.Visitor;
import org.youcode.WRM_V1.core.entities.visitor.mappers.CreateVisitorDTOToVisitorEntityMapper;
import org.youcode.WRM_V1.core.entities.visitor.mappers.VisitorEntityToVisitorResponseDTOMapper;
import org.youcode.WRM_V1.core.exceptions.EntityNotFoundException;
import org.youcode.WRM_V1.infra.adapters.outbound.persistence.VisitPersistenceAdapter;
import org.youcode.WRM_V1.infra.adapters.outbound.persistence.VisitorPersistenceAdapter;

@Service
public class VisitorServiceImp implements VisitorService {

    private final VisitorPersistenceAdapter visitorPersistenceAdapter;
    private final CreateVisitorDTOToVisitorEntityMapper createVisitorDTOToVisitorEntityMapper;
    private final VisitorEntityToVisitorResponseDTOMapper visitorEntityToVisitorResponseDTOMapper;



    public VisitorServiceImp(VisitorPersistenceAdapter visitorPersistenceAdapter , CreateVisitorDTOToVisitorEntityMapper createVisitorDTOToVisitorEntityMapper , VisitorEntityToVisitorResponseDTOMapper visitorEntityToVisitorResponseDTOMapper, VisitPersistenceAdapter visitPersistenceAdapter){
        this.createVisitorDTOToVisitorEntityMapper = createVisitorDTOToVisitorEntityMapper;
        this.visitorPersistenceAdapter = visitorPersistenceAdapter;
        this.visitorEntityToVisitorResponseDTOMapper = visitorEntityToVisitorResponseDTOMapper;
    }

    @Override
    public VisitorResponseDTO save(CreateAndUpdateVisitorDTO data){
        Visitor visitorToCreate = createVisitorDTOToVisitorEntityMapper.toEntity(data);
        Visitor createdVisitor = visitorPersistenceAdapter.save(visitorToCreate);
        return visitorEntityToVisitorResponseDTOMapper.entityToDto(createdVisitor);
    }

    @Override
    public VisitorResponseDTO getVisitorById(Long id){
        Visitor v = visitorPersistenceAdapter.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("No visitor found with given ID !"));
        return visitorEntityToVisitorResponseDTOMapper.entityToDto(v);
    }

    @Override
    public VisitorResponseDTO update(CreateAndUpdateVisitorDTO data , Long id){
        Visitor v = visitorPersistenceAdapter.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Visitor found with given ID !"));
        Visitor visitorToUpdate = createVisitorDTOToVisitorEntityMapper.toEntity(data);
        visitorToUpdate.setId(id);
        Visitor updateVisitor = visitorPersistenceAdapter.save(visitorToUpdate);
        return visitorEntityToVisitorResponseDTOMapper.entityToDto(updateVisitor);
    }

    @Override
    public Page<VisitorResponseDTO> getAllVisitors(Pageable pageable){
        Page<Visitor> visitors = visitorPersistenceAdapter.findAll(pageable);
        return visitors.map(visitorEntityToVisitorResponseDTOMapper::entityToDto);
    }

    @Override
    public VisitorResponseDTO delete(Long id){
        Visitor v = visitorPersistenceAdapter.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Visitor found with given ID !"));
        visitorPersistenceAdapter.deleteById(id);
        return visitorEntityToVisitorResponseDTOMapper.entityToDto(v);
    }
}
