package com.flycatch.natheer.mock.service.payloads.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddedPersonResponse {
    private PersonDetailsResponse person;
    private boolean result;
    private ResultStatusResponse resultDetails;
}
