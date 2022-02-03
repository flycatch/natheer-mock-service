package com.flycatch.natheer.mock.service.repository;

import com.flycatch.natheer.mock.service.models.PrimaryIdNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NatheerNotificationRepository extends JpaRepository<PrimaryIdNotification, Integer> {
}
