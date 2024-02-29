package com.learnms.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import static com.learnms.accounts.constants.ErrorConstants.*;

/**
 * Dto class to send as a response to the calling api.
 */
@Data
@Builder
@Schema(
        name = "Account Details",
        description = "Schema to hold account details"
)
public class AccountsDto {

    @NotEmpty(message = ERR_EMPTY_ACCOUNT_NUM)
    private Long accountNumber;

    @NotEmpty(message = ERR_EMPTY_ACCOUNT_TYPE)
    private String accountType;

    @NotEmpty(message = ERR_EMPTY_ACCOUNT_BRANCH)
    private String branch;
}
