package org.youcode.WRM_V1.app.ports.outbound;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.youcode.WRM_V1.common.interfaces.GenericDao;
import org.youcode.WRM_V1.core.entities.embeddables.VisitKey;
import org.youcode.WRM_V1.core.entities.visit.Visit;



public interface VisitDAO extends GenericDao<Visit , VisitKey> {
    Page<Visit> findAll(Pageable pageable);

}
