package org.youcode.WRM_V1.infra.adapters.outbound.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.youcode.WRM_V1.app.ports.outbound.VisitDAO;
import org.youcode.WRM_V1.core.entities.embeddables.VisitKey;
import org.youcode.WRM_V1.core.entities.visit.Visit;

@Repository
public interface VisitPersistenceAdapter extends VisitDAO , JpaRepository<Visit , VisitKey> {
}
