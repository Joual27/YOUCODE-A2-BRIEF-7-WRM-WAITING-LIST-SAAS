package org.youcode.WRM_V1.app.ports.outbound;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.youcode.WRM_V1.common.interfaces.GenericDao;
import org.youcode.WRM_V1.core.entities.visitor.Visitor;

public interface VisitorDAO extends GenericDao<Visitor , Long> {
    Page<Visitor> findAll(Pageable pageable);
}
