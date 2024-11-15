package org.youcode.WRM_V1.core.entities.waitingList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.youcode.WRM_V1.common.BaseEntity;
import org.youcode.WRM_V1.core.entities.visit.Visit;

import java.time.LocalDate;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WaitingList extends BaseEntity {
    @Column(name = "DATE")
    private LocalDate date;

    @Column(name = "ALGORITHM")
    private String algorithm;

    @Column(name = "CAPACITY")
    private Integer capacity;

    @OneToMany(mappedBy = "waitingList" , fetch = FetchType.EAGER)
    private List<Visit> visits;
}
