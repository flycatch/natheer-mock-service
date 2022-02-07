package com.flycatch.natheer.mock.service.payloads.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDetailsResponse {
    private String personId;
    private LocalDate personDob;
}
