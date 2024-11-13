package org.youcode.WRM.app.ports.outbound;

import org.youcode.WRM.common.interfaces.GenericDao;
import org.youcode.WRM.core.entities.embeddables.VisitKey;
import org.youcode.WRM.core.entities.visit.Visit;

public interface VisitDAO extends GenericDao<Visit , VisitKey> {
}
