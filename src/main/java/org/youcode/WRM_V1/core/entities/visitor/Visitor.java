package org.youcode.WRM_V1.core.entities.visitor;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.youcode.WRM_V1.common.BaseEntity;
import org.youcode.WRM_V1.core.entities.visit.Visit;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Visitor extends BaseEntity {
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @OneToMany(mappedBy = "visitor" , fetch = FetchType.EAGER)
    private Set<Visit> visits;
}
