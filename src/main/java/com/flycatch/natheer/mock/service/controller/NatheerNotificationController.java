package com.flycatch.natheer.mock.service.controller;

import com.flycatch.natheer.mock.service.Constants;
import com.flycatch.natheer.mock.service.payloads.request.PrimaryIdNotificationRequest;
import com.flycatch.natheer.mock.service.service.natheer.NatheerNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NatheerNotificationController {

    private final NatheerNotificationService natheerNotificationService;

    @PostMapping("/primaryIdNotification")
    public ResponseEntity<String> notify(@RequestBody List<PrimaryIdNotificationRequest> requests) {
        natheerNotificationService.saveNotification(requests);
        return ResponseEntity.ok()
                .body(Constants.SUCCESS);
    }
}
