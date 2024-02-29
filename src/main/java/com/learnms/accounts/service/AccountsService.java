package com.learnms.accounts.service;

import com.learnms.accounts.dto.CustomerDto;
import org.springframework.http.ResponseEntity;

/**
 * Service class for putting accounts logic.
 */
public interface AccountsService {

    /**
     * This method will create the account for the provided customer details.
     * @param customerDto {@link CustomerDto}
     */
    void createAccount(CustomerDto customerDto);

    /**
     * To check if the customer record already exist with the provided mobile number.
     * @param mobileNumber for which record need to be check
     * @return true if exist else false
     */
    boolean isCustomerAlreadyPresentWithMobileNumber(String mobileNumber);

    /**
     * Method to fetch the customer details for the provided mobile.
     * @param mobileNumber for which record need to be searched
     * @return {@link CustomerDto}
     */
    ResponseEntity<CustomerDto> getCustomerByMobileNumber(String mobileNumber);

    /**
     * Update customer details for the provided details if exist else return error.
     * @param customerDto {@link CustomerDto}
     * @return {@link ResponseEntity<CustomerDto>} updated customer record
     */
    ResponseEntity<CustomerDto> updateAccount(CustomerDto customerDto);
}
