package org.youcode.WRM.core.entities.waitingList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.youcode.WRM.common.BaseEntity;
import org.youcode.WRM.core.entities.visit.Visit;

import java.time.LocalDate;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WaitingList extends BaseEntity {
    @Column(name = "DATE")
    private LocalDate date;

    @Column(name = "ALGORITHM")
    private String algorithm;

    @Column(name = "CAPACITY")
    private int capacity;

    @OneToMany(mappedBy = "waitingList")
    private Set<Visit> visits;
}
