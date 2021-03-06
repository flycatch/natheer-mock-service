package com.flycatch.natheer.mock.service.controller;

import com.flycatch.natheer.mock.service.Constants;
import com.flycatch.natheer.mock.service.models.NatheerPersonDetails;
import com.flycatch.natheer.mock.service.models.ResultStatus;
import com.flycatch.natheer.mock.service.payloads.ApiResponse;
import com.flycatch.natheer.mock.service.payloads.request.AddPersonBulkRequest;
import com.flycatch.natheer.mock.service.payloads.request.PrimaryIdNotificationRequest;
import com.flycatch.natheer.mock.service.payloads.response.AddedPersonResponse;
import com.flycatch.natheer.mock.service.payloads.response.PersonDetailsResponse;
import com.flycatch.natheer.mock.service.payloads.response.ResultStatusResponse;
import com.flycatch.natheer.mock.service.service.addedperson.PersonBulkAddService;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequiredArgsConstructor
@Slf4j
public class NatheerMockController {

    @Value("${app.util.clientUrl}")
    private String clientUrl;

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
    public ResponseEntity<ApiResponse> primaryIdNotification(@PathVariable Long personId) {
        log.info("trigerring primary id notification");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", createHeaders(username, password));
        PrimaryIdNotificationRequest primaryIdNotificationRequest = new PrimaryIdNotificationRequest();
        primaryIdNotificationRequest.setPrimaryId(personId);
        primaryIdNotificationRequest.setNotificationId(183);
        primaryIdNotificationRequest.setNotificationCode(1000);
        List<PrimaryIdNotificationRequest> list = new ArrayList<>();
        list.add(primaryIdNotificationRequest);
        HttpEntity<List<PrimaryIdNotificationRequest>> request = new HttpEntity<>(list, headers);
        RestTemplate restTemplate = new RestTemplate();
        log.info("sending notification");
        restTemplate.postForEntity(clientUrl, request, String.class);
        log.info("request send");
        return ApiResponse.create(HttpStatus.OK, true, Constants.SUCCESS);
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
            var dob = natheerPersonDetails1.getPersonDob();
            var s = dob.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            LocalDate date = LocalDate.parse(s, formatter);
            personDetailsResponse.setPersonDob(date);
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
