package com.flycatch.natheer.mock.service.service.addedperson;

import com.flycatch.natheer.mock.service.models.NatheerPersonDetails;
import com.flycatch.natheer.mock.service.payloads.request.AddPersonBulkRequest;

import java.util.Set;

public interface PersonBulkAddService {
    Set<NatheerPersonDetails> addPersonDetails(Set<AddPersonBulkRequest> addPersonBulkRequestList);
}
