package com.flycatch.natheer.mock.service.service.natheer;

import com.flycatch.natheer.mock.service.payloads.request.PrimaryIdNotificationRequest;

import java.util.List;

public interface NatheerNotificationService {
    void saveNotification(List<PrimaryIdNotificationRequest> requests);
}
