package org.youcode.WRM_V1.app.ports.outbound;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.youcode.WRM_V1.common.interfaces.GenericDao;
import org.youcode.WRM_V1.core.entities.visit.Visit;
import org.youcode.WRM_V1.core.entities.waitingList.WaitingList;


import java.util.List;

public interface WaitingListDAO extends GenericDao<WaitingList , Long> {
    @Query("FROM Visit v WHERE v.waitingList = :waitingList ORDER BY v.arrivalTime ASC")
    List<Visit> getVisitsOfWaitingListOrderedByFifo(@Param("waitingList") WaitingList waitingList);
    @Query("FROM Visit v WHERE v.waitingList = :waitingList ORDER BY v.estimatedVisitTime ASC")
    List<Visit> getVisitsOfWaitingListOrderedBySJF(@Param("waitingList") WaitingList waitingList);
    @Query("FROM Visit v WHERE v.waitingList = :waitingList ORDER BY v.priority ASC")
    List<Visit> getVisitsOfWaitingListOrderedByPF(@Param("waitingList") WaitingList waitingList);
    Page<WaitingList> findAll(Pageable pageable);
}
