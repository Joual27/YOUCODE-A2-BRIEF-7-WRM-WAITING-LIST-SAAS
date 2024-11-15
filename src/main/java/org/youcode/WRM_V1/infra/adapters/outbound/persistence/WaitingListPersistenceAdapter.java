package org.youcode.WRM_V1.infra.adapters.outbound.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.youcode.WRM_V1.app.ports.outbound.WaitingListDAO;
import org.youcode.WRM_V1.core.entities.waitingList.WaitingList;

@Repository
public interface WaitingListPersistenceAdapter extends WaitingListDAO , JpaRepository<WaitingList , Long> {
}
