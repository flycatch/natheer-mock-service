package com.flycatch.natheer.mock.service.controller;

import com.flycatch.natheer.mock.service.Constants;
import com.flycatch.natheer.mock.service.models.NatheerPersonDetails;
import com.flycatch.natheer.mock.service.models.ResultStatus;
import com.flycatch.natheer.mock.service.payloads.request.AddPersonBulkRequest;
import com.flycatch.natheer.mock.service.payloads.request.PrimaryIdNotificationRequest;
import com.flycatch.natheer.mock.service.payloads.response.AddedPersonResponse;
import com.flycatch.natheer.mock.service.payloads.response.PersonDetailsResponse;
import com.flycatch.natheer.mock.service.payloads.response.ResultStatusResponse;
import com.flycatch.natheer.mock.service.service.addedperson.PersonBulkAddService;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequiredArgsConstructor
public class NatheerMockController {

    @Value("${app.util.clientUrl}")
    private String  clientUrl;

    @Value("${app.util.username}")
    private String username;

    @Value("${app.util.password}")
    private String password;

    private final PersonBulkAddService personBulkAddService;

    @PostMapping("/natheer-services/watchlist/person/bulk/add")
    public Set<AddedPersonResponse> addPersonBulk(@RequestBody Set<AddPersonBulkRequest> addPersonBulkRequests) {
        return createNatheerResponse(personBulkAddService.addPersonDetails(addPersonBulkRequests));
    }

    @GetMapping("/trigger-primary-id/{personId}")
    public ResponseEntity<String> primaryIdNotification(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization" , createHeaders(username, password));
        PrimaryIdNotificationRequest primaryIdNotificationRequest = new PrimaryIdNotificationRequest();
        primaryIdNotificationRequest.setPrimaryId(id);
        primaryIdNotificationRequest.setNotificationId(183);
        primaryIdNotificationRequest.setNotificationCode(1000);
        HttpEntity<PrimaryIdNotificationRequest> request = new HttpEntity<>(primaryIdNotificationRequest, headers);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(clientUrl, request, String.class);
        return ResponseEntity.ok()
                .body(Constants.SUCCESS);
    }

    String createHeaders(String username, String password) {
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(Charset.forName("US-ASCII")));
        return "Basic " + new String(encodedAuth);
    }

    private static Set<AddedPersonResponse> createNatheerResponse(Set<NatheerPersonDetails> natheerPersonDetails) {
        Set<AddedPersonResponse> addedPersonResponses = new HashSet<>();
        for (NatheerPersonDetails natheerPersonDetails1 : natheerPersonDetails) {
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
