package com.learnms.accounts.controller;

import static com.learnms.accounts.constants.AccountsContants.ACCOUNTS_CREATED_SUCCESS_MSJ;

import com.learnms.accounts.dto.CustomerDto;
import com.learnms.accounts.dto.ErrorResponseDto;
import com.learnms.accounts.dto.ResponseDto;
import com.learnms.accounts.service.AccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts/api/")
@AllArgsConstructor
@Validated
@Tag(
        name = "CRUD REST APIs to interact with Accounts Service",
        description = "REST api to CREATE, UPDATE and VIEW the customer records"
)
public class AccountsController {

    private final AccountsService accountsService;

    @Operation(
            summary = "CREATE api in account service",
            description = "CREATE api to create the customer records in accounts service"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Account created"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = @Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
            ))

    })
    @PostMapping(value = "/createAccount",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        accountsService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.CREATED.value(),ACCOUNTS_CREATED_SUCCESS_MSJ));
    }

    @Operation(
            summary = "GET api in account service",
            description = "This api will fetch the customer record for the provided mobile number"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Details fetched"),
            @ApiResponse(responseCode = "404", description = "Record not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping(value = "/getCustomer/{mobileNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> getCustomerByMobileNumber(@PathVariable("mobileNumber") String mobileNumber) {
        return accountsService.getCustomerByMobileNumber(mobileNumber);
    }

    @Operation(
            summary = "UPDATE api in account service",
            description = "This api will update the existing customer record for the provided details"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Updated successfully"),
            @ApiResponse(responseCode = "404", description = "Record not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PutMapping(value = "/updateAccount", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> updateAccount(@Valid @RequestBody CustomerDto customerDto) {
        return accountsService.updateAccount(customerDto);
    }

}
