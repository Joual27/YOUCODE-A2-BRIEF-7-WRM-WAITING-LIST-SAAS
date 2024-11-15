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
    @Query("FROM Visit v WHERE v.waitingList = :waitingList")
    List<Visit> getVisitsOfWaitingList(@Param("waitingList") WaitingList waitingList);

    Page<WaitingList> findAll(Pageable pageable);
}
