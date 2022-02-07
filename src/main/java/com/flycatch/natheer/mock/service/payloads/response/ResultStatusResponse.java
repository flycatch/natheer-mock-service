package com.flycatch.natheer.mock.service.payloads.response;

import com.flycatch.natheer.mock.service.models.ResultStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResultStatusResponse {
    private String resultCode;
    private ResultStatus resultDetails;
}
