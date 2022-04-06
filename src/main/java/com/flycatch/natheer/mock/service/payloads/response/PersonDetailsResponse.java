package com.flycatch.natheer.mock.service.payloads.response;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDate personDob;
}
