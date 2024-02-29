package com.learnms.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import static com.learnms.accounts.constants.ErrorConstants.*;

/**
 * Dto class of customer to send as a response to the calling api.
 */
@Data
@Builder
@Schema(
        name = "Customer Details",
        description = "Schema to hold customer and account details"
)
public class CustomerDto {

    @NotEmpty(message = ERR_EMPTY_NAME)
    private String name;

    @NotEmpty(message = ERR_EMPTY_EMAIL) @Email(message = ERR_INVALID_EMAIL)
    private String email;

    @NotEmpty(message = ERR_EMPTY_MOBILE) @Pattern(regexp = "(^$|[0-9]{10})", message = ERR_INVALID_MOBILE)
    private String mobileNumber;

    private AccountsDto accountsDto;
}
