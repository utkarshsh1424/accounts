package com.learnms.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Error dto for handling if api call non-success.
 */
@Data
@AllArgsConstructor
@Schema(
        name = "Error response",
        description = "Response object to hold error response"
)
public class ErrorResponseDto {

    private String apiPath;
    private HttpStatus errorCode;
    private String errMsj;
    private LocalDateTime errorTime;
}
