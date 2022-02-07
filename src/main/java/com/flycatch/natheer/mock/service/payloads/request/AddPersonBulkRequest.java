package com.flycatch.natheer.mock.service.payloads.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddPersonBulkRequest {
    private PersonDetailRequest person;
}
