package org.youcode.WRM_V1.app.ports.inbound;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.youcode.WRM_V1.core.entities.visit.Visit;
import org.youcode.WRM_V1.core.entities.waitingList.DTOs.CreateAndUpdateWaitingListDTO;
import org.youcode.WRM_V1.core.entities.waitingList.DTOs.WaitingListResponseDTO;
import org.youcode.WRM_V1.core.entities.waitingList.WaitingList;

import java.util.List;

public interface WaitingListService {
    List<Visit> getVisitsOfWaitingList(WaitingList waitingList);
    WaitingListResponseDTO save(CreateAndUpdateWaitingListDTO data);
    Page<WaitingListResponseDTO> getAll(Pageable pageable);
    WaitingListResponseDTO update(Long id , CreateAndUpdateWaitingListDTO data);
    WaitingListResponseDTO getWaitingListById(Long id);
    WaitingListResponseDTO delete(Long id);
}
