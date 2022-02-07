package com.flycatch.natheer.mock.service.repository;

import com.flycatch.natheer.mock.service.models.NatheerPersonDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NatheerPersonDetailsRepository extends JpaRepository<NatheerPersonDetails, Long> {
}
