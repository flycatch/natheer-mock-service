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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequiredArgsConstructor
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
        return  createNatheerResponse(personBulkAddService.addPersonDetails(addPersonBulkRequests));
    }

    @PostMapping("/primaryIdNotification")
    public ResponseEntity<String> primaryIdNotification(@RequestBody PrimaryIdNotificationRequest primaryIdNotificationRequest) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>(createHeaders(username, password));
        restTemplate.exchange(clientUrl, HttpMethod.POST,
                request, primaryIdNotificationRequest.getClass());
        return ResponseEntity.ok()
                .body(Constants.SUCCESS);
    }

    HttpHeaders createHeaders(String username, String password){
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")) );
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );
        }};
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
