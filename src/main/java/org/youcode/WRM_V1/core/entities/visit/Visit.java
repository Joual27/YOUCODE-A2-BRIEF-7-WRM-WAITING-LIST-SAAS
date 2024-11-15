package org.youcode.WRM_V1.core.entities.visit;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.youcode.WRM_V1.core.entities.embeddables.VisitKey;
import org.youcode.WRM_V1.core.entities.visitor.Visitor;
import org.youcode.WRM_V1.core.entities.waitingList.WaitingList;

import java.time.Duration;
import java.time.LocalDateTime;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Visit {

    @EmbeddedId
    private VisitKey id;

    @Column(name = "ARRIVAL_TIME")
    private LocalDateTime arrivalTime;

    @Column(name = "START_TIME")
    private LocalDateTime startDate;

    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "PRIORITY")
    private int priority;

    @Column(name = "ESTIMATED_VISIT_TIME")
    private Duration estimatedVisitTime;

    @ManyToOne
    @JoinColumn(name = "VISITOR_ID")
    @MapsId("visitorId")
    private Visitor visitor;

    @ManyToOne
    @JoinColumn(name = "WAITING_LIST_ID")
    @MapsId("waitingListId")
    private WaitingList waitingList;
}
