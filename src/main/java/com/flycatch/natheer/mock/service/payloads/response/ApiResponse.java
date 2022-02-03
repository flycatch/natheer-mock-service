package com.flycatch.natheer.mock.service.payloads.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import java.util.Collection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


/**
 * Api Response class for all responses.
 */
@Setter
@Getter
@NoArgsConstructor
public class ApiResponse {

    private LocalDateTime timestamp;
    private String message;
    private boolean success;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object[] data;

    /**
     * Api Response is used for all api responses.
     *
     * @param message Message to be returned.
     * @param success Status of the Api.
     * @param data    The data that should send to user.
     */
    public ApiResponse(String message, boolean success, Object[] data) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.success = success;
        this.data = data;
    }

    public static <T> ResponseEntity<ApiResponse> create(HttpStatus status, boolean success, String message, Collection<T> data) {
        return create(status, success, message, data.toArray());
    }

    public static ResponseEntity<ApiResponse> create(HttpStatus status, boolean success, String message, Object... data) {
        ApiResponse response = new ApiResponse(message, success, data);
        return new ResponseEntity<>(response, status);
    }

    public static ResponseEntity<ApiResponse> create(HttpStatus status, boolean success, String message) {
        ApiResponse response = new ApiResponse(message, success, null);
        return new ResponseEntity<>(response, status);
    }
}
