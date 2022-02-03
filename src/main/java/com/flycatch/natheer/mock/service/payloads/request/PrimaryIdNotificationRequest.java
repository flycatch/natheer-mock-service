package com.flycatch.natheer.mock.service.payloads.request;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrimaryIdNotificationRequest {

    private int notificationId;
    private int notificationCode;
    private long primaryId;
    private JsonNode parameterList;
}
