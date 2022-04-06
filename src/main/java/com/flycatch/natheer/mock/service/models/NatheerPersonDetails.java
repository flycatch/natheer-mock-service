package com.flycatch.natheer.mock.service.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@Entity
@Table(name = "natheer_person_details")
public class NatheerPersonDetails extends BaseEntity{
    private String personId;
    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDate personDob;
}
