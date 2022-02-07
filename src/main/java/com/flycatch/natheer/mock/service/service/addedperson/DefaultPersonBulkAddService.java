package com.flycatch.natheer.mock.service.service.addedperson;

import com.flycatch.natheer.mock.service.models.NatheerPersonDetails;
import com.flycatch.natheer.mock.service.payloads.request.AddPersonBulkRequest;
import com.flycatch.natheer.mock.service.repository.NatheerPersonDetailsRepository;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultPersonBulkAddService implements PersonBulkAddService {

    private final NatheerPersonDetailsRepository natheerPersonDetailsRepository;

    @Override
    public Set<NatheerPersonDetails> addPersonDetails(Set<AddPersonBulkRequest> addPersonBulkRequests) {
        log.info("adding bulk person details");
        Set<NatheerPersonDetails> natheerPersonDetails = new HashSet<>();
        for (AddPersonBulkRequest addPersonBulkRequest : addPersonBulkRequests) {
            NatheerPersonDetails natheerPersonDetails1 = new NatheerPersonDetails();
            natheerPersonDetails1.setPersonId(addPersonBulkRequest.getPerson().getPersonId());
            natheerPersonDetails1.setPersonDob(addPersonBulkRequest.getPerson().getPersonDob());
            natheerPersonDetailsRepository.save(natheerPersonDetails1);
            natheerPersonDetails.add(natheerPersonDetails1);
        }
        return natheerPersonDetails;
    }
}
