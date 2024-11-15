package org.youcode.WRM_V1.core.entities.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VisitKey {
    @Column(name = "VISITOR_ID")
    private Long visitorId;

    @Column(name = "WAITING_LIST_ID")
    private Long waitingListId;
}
