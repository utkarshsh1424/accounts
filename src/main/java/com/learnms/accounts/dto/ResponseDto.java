package com.learnms.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Dto class for api responses.
 */
@Data
@AllArgsConstructor
@Schema(
        name = "Response Object",
        description = "Response object to hold success response"
)
public class ResponseDto {

    private int statusCode;
    private String statusMsj;
}
