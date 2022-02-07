package com.flycatch.natheer.mock.service.controller;

import com.flycatch.natheer.mock.service.Constants;
import com.flycatch.natheer.mock.service.models.NatheerPersonDetails;
import com.flycatch.natheer.mock.service.models.ResultStatus;
import com.flycatch.natheer.mock.service.payloads.request.AddPersonBulkRequest;
import com.flycatch.natheer.mock.service.payloads.response.AddedPersonResponse;
import com.flycatch.natheer.mock.service.payloads.response.PersonDetailsResponse;
import com.flycatch.natheer.mock.service.payloads.response.ResultStatusResponse;
import com.flycatch.natheer.mock.service.service.addedperson.PersonBulkAddService;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequiredArgsConstructor
public class NatheerMockController {

    private final PersonBulkAddService personBulkAddService;

    @PostMapping("/natheer-services/watchlist/person/bulk/add")
    public Set<AddedPersonResponse> addPersonBulk(@RequestBody Set<AddPersonBulkRequest> addPersonBulkRequests) {
        return  createNatheerResponse(personBulkAddService.addPersonDetails(addPersonBulkRequests));
    }

    @GetMapping("/primaryIdNotification")
    public ResponseEntity<String> primaryIdNotification() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject("http://34.122.34.215:8001/login", String.class);
        return ResponseEntity.ok()
                .body(Constants.SUCCESS);
    }

    private static Set<AddedPersonResponse> createNatheerResponse(Set<NatheerPersonDetails> natheerPersonDetails) {
        Set<AddedPersonResponse> addedPersonResponses = new HashSet<>();
        for(NatheerPersonDetails natheerPersonDetails1 : natheerPersonDetails) {
            AddedPersonResponse addedPersonResponse = new AddedPersonResponse();
            PersonDetailsResponse personDetailsResponse = new PersonDetailsResponse();
            personDetailsResponse.setPersonId(natheerPersonDetails1.getPersonId());
            personDetailsResponse.setPersonDob(natheerPersonDetails1.getPersonDob());
            addedPersonResponse.setPerson(personDetailsResponse);
            ResultStatusResponse response = new ResultStatusResponse();
            if (natheerPersonDetails1.getPersonId().length() != 10) {
                addedPersonResponse.setResult(false);
                response.setResultCode("101");
                response.setResultDetails(ResultStatus.INVALID_ID);
                addedPersonResponse.setResultDetails(response);
            } else {
                addedPersonResponse.setResult(true);
            }
            addedPersonResponses.add(addedPersonResponse);
        }
        return addedPersonResponses;

    }
}
