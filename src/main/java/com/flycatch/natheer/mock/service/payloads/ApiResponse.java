package com.flycatch.natheer.mock.service.payloads;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.sql.Timestamp;
import java.util.Collection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Setter
@Getter
@NoArgsConstructor
public class ApiResponse {

    private Timestamp timestamp;
    private String message;
    private boolean success;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object[] data;

    /**
     * Constructor for Api response class .
     */
    public ApiResponse(String message, boolean success, Object[] data) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
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
