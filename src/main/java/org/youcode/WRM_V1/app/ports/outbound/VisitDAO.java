package org.youcode.WRM_V1.app.ports.outbound;


import org.youcode.WRM_V1.common.interfaces.GenericDao;
import org.youcode.WRM_V1.core.entities.embeddables.VisitKey;
import org.youcode.WRM_V1.core.entities.visit.Visit;


public interface VisitDAO extends GenericDao<Visit , VisitKey> {

}
