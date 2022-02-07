package com.flycatch.natheer.mock.service.models;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "natheer_person_details")
public class NatheerPersonDetails extends BaseEntity{
    private String personId;
    private LocalDate personDob;
}
