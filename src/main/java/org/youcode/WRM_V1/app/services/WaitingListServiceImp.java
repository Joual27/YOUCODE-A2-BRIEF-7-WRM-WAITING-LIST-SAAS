package org.youcode.WRM_V1.app.services;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.youcode.WRM_V1.app.ports.inbound.WaitingListService;
import org.youcode.WRM_V1.core.entities.visit.Visit;
import org.youcode.WRM_V1.core.entities.waitingList.DTOs.CreateAndUpdateWaitingListDTO;
import org.youcode.WRM_V1.core.entities.waitingList.DTOs.WaitingListResponseDTO;
import org.youcode.WRM_V1.core.entities.waitingList.WaitingList;
import org.youcode.WRM_V1.core.entities.waitingList.mappers.CreateWaitingListDTOToWaitingListEntityMapper;
import org.youcode.WRM_V1.core.entities.waitingList.mappers.WaitingListEntityToWaitingListResponseDTOMapper;
import org.youcode.WRM_V1.core.exceptions.EntityNotFoundException;
import org.youcode.WRM_V1.core.exceptions.NegativeCapacityException;
import org.youcode.WRM_V1.infra.adapters.outbound.persistence.WaitingListPersistenceAdapter;

import java.util.List;


@Service
public class WaitingListServiceImp implements WaitingListService {

    @Getter
    @Value("${APP_DEFAULT_ALGORITHM}")
    private String defaultAlgorithm;
    @Getter
    @Value("${APP_DEFAULT_CAPACITY}")
    private Integer defaultCapacity;


    private final WaitingListPersistenceAdapter waitingListPersistenceAdapter;
    private final CreateWaitingListDTOToWaitingListEntityMapper createWaitingListDTOToWaitingListEntityMapper;
    private final WaitingListEntityToWaitingListResponseDTOMapper waitingListEntityToWaitingListResponseDTOMapper;

    public WaitingListServiceImp(WaitingListPersistenceAdapter waitingListPersistenceAdapter , CreateWaitingListDTOToWaitingListEntityMapper createWaitingListDTOToWaitingListEntityMapper , WaitingListEntityToWaitingListResponseDTOMapper waitingListEntityToWaitingListResponseDTOMapper){
        this.waitingListPersistenceAdapter = waitingListPersistenceAdapter ;
        this.createWaitingListDTOToWaitingListEntityMapper = createWaitingListDTOToWaitingListEntityMapper;
        this.waitingListEntityToWaitingListResponseDTOMapper = waitingListEntityToWaitingListResponseDTOMapper;
    }

    @Override
    public WaitingListResponseDTO save(CreateAndUpdateWaitingListDTO data){
        WaitingList waitingListToCreate = createWaitingListDTOToWaitingListEntityMapper.toEntity(data);
        if (data.algorithm() == null){
            waitingListToCreate.setAlgorithm(defaultAlgorithm);
        }
        if (data.capacity() == null){
           waitingListToCreate.setCapacity(defaultCapacity);
        }
        else if (data.capacity() <= 0 ){
            throw new NegativeCapacityException("Capacity can't be negative");
        }
        WaitingList createdWaitingList = waitingListPersistenceAdapter.save(waitingListToCreate);
        return waitingListEntityToWaitingListResponseDTOMapper.entityToDto(createdWaitingList);
    }

    @Override
    public Page<WaitingListResponseDTO> getAll(Pageable pageable) {
        Page<WaitingList> waitingLists = waitingListPersistenceAdapter.findAll(pageable);
        return waitingLists.map(waitingList -> {
            List<Visit> orderedVisits = getOrderedVisits(waitingList.getAlgorithm(), waitingList);
            waitingList.setVisits(orderedVisits);
            return waitingListEntityToWaitingListResponseDTOMapper.entityToDto(waitingList);
        });
    }


    @Override
    public WaitingListResponseDTO update(Long id, CreateAndUpdateWaitingListDTO data){
        WaitingList w = waitingListPersistenceAdapter.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No waiting list was found with given id !"));
        if (data.capacity() <= 0){
            throw new NegativeCapacityException("Capacity Can't be negative ! ");
        }
        WaitingList waitingListToUpdate = createWaitingListDTOToWaitingListEntityMapper.toEntity(data);
        waitingListToUpdate.setId(id);
        WaitingList updatedWaitingList = waitingListPersistenceAdapter.save(waitingListToUpdate);
        return waitingListEntityToWaitingListResponseDTOMapper.entityToDto(updatedWaitingList);
    }

    @Override
    public WaitingListResponseDTO getWaitingListById(Long id){
        WaitingList w = waitingListPersistenceAdapter.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No waiting list was found with given id !"));
        w.setVisits(getOrderedVisits(w.getAlgorithm(),w));
        return waitingListEntityToWaitingListResponseDTOMapper.entityToDto(w);
    }

    @Override
    public WaitingListResponseDTO delete(Long id){
        WaitingList w = waitingListPersistenceAdapter.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No waiting list was found with given id !"));
        waitingListPersistenceAdapter.deleteById(id);
        return waitingListEntityToWaitingListResponseDTOMapper.entityToDto(w);
    }

    private List<Visit> getOrderedVisits(String algorithm , WaitingList w){
        switch (algorithm){
            case "FIFO":
                return waitingListPersistenceAdapter.getVisitsOfWaitingListOrderedByFifo(w);
            case "SJF":
                return waitingListPersistenceAdapter.getVisitsOfWaitingListOrderedBySJF(w);
            case "PF":
                return waitingListPersistenceAdapter.getVisitsOfWaitingListOrderedByPF(w);
            default:
                return List.of();
        }
    }
}

