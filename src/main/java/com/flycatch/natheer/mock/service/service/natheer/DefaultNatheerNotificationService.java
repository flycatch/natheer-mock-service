package com.flycatch.natheer.mock.service.service.natheer;

import com.flycatch.natheer.mock.service.models.PrimaryIdNotification;
import com.flycatch.natheer.mock.service.payloads.request.PrimaryIdNotificationRequest;
import com.flycatch.natheer.mock.service.repository.NatheerNotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultNatheerNotificationService implements NatheerNotificationService {

    private final NatheerNotificationRepository natheerNotificationRepository;

    @Override
    public void saveNotification(List<PrimaryIdNotificationRequest> requests) {
        log.info("notification from natheer");
        requests.forEach(data -> {
            PrimaryIdNotification primaryIdNotification = new PrimaryIdNotification();
            primaryIdNotification.setNotificationId(data.getNotificationId());
            primaryIdNotification.setNotificationCode(data.getNotificationCode());
            primaryIdNotification.setCitizenId(data.getPrimaryId());
            primaryIdNotification.setParameter(data.getParameterList().toString());
            natheerNotificationRepository.save(primaryIdNotification);
        });
    }
}
