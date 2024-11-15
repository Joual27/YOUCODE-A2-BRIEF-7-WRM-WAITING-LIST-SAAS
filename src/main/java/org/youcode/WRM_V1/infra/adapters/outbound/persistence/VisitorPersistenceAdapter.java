package org.youcode.WRM_V1.infra.adapters.outbound.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.youcode.WRM_V1.app.ports.outbound.VisitorDAO;
import org.youcode.WRM_V1.core.entities.visitor.Visitor;


@Repository
public interface VisitorPersistenceAdapter extends VisitorDAO, JpaRepository<Visitor, Long> {
}
