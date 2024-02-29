package com.learnms.accounts.service.impl;

import com.learnms.accounts.constants.AccountsContants;
import com.learnms.accounts.constants.ErrorConstants;
import com.learnms.accounts.dto.AccountsDto;
import com.learnms.accounts.dto.CustomerDto;
import com.learnms.accounts.entity.Accounts;
import com.learnms.accounts.entity.Customer;
import com.learnms.accounts.exceptions.CustomerAlreadyExistException;
import com.learnms.accounts.exceptions.ResourceNotFoundException;
import com.learnms.accounts.repository.AccountsRepository;
import com.learnms.accounts.repository.CustomerRepository;
import com.learnms.accounts.service.AccountsService;
import com.learnms.accounts.util.AccountsMapper;
import com.learnms.accounts.util.CustomerMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

/**
 * Impl class for {@link AccountsService}
 */
@Service
@AllArgsConstructor
public class AccountsServiceImpl implements AccountsService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;

    /**
     * This method will create the account for the provided customer details.
     *
     * @param customerDto {@link CustomerDto}
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        if (isCustomerAlreadyPresentWithMobileNumber(customerDto.getMobileNumber())) {
            throw new CustomerAlreadyExistException(ErrorConstants.ERR_CUSTOMER_ALREADY_EXIST
                    + " with mobile number : " + customerDto.getMobileNumber());
        }
        Customer customerToBeSaved = CustomerMapper.mapToCustomer(customerDto);
        Customer savedCustomer = customerRepository.save(customerToBeSaved);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    /**
     * To check if the customer record already exist with the provided mobile number.
     *
     * @param mobileNumber for which record need to be check
     * @return true if exist else false
     */
    @Override
    public boolean isCustomerAlreadyPresentWithMobileNumber(String mobileNumber) {
        Optional<Customer> customer = customerRepository.findByMobile(mobileNumber);
        if (customer.isPresent()) {
            return true;
        }
        return false;
    }

    /**
     * Method to fetch the customer details for the provided mobile.
     *
     * @param mobileNumber for which record need to be searched
     * @return {@link CustomerDto}
     */
    @Override
    public ResponseEntity<CustomerDto> getCustomerByMobileNumber(String mobileNumber) {
        Optional<Customer> customer = customerRepository.findByMobile(mobileNumber);
        if (customer.isEmpty()) {
            throw new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber);
        }
        return new ResponseEntity<>(CustomerMapper.mapToCustomerDto(customer.get()), HttpStatus.OK);
    }

    /**
     * Update customer details for the provided details if exist else return error.
     *
     * @param customerDto {@link CustomerDto}
     * @return {@link ResponseEntity<CustomerDto>} updated customer record
     */
    @Override
    public ResponseEntity<CustomerDto> updateAccount(CustomerDto customerDto) {
        AccountsDto accountsFromCustomerDtoReqObj = customerDto.getAccountsDto();
        Accounts accountAlreadyPresent = accountsRepository.findById(accountsFromCustomerDtoReqObj.getAccountNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "AccountNumber", accountsFromCustomerDtoReqObj.getAccountNumber().toString())
        );
        Long customerId = accountAlreadyPresent.getCustomerId();
        Customer customerAlreadyPresent = customerRepository.findById(customerId).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
        );
        Accounts accountsToBeUpdated = AccountsMapper.mapToAccounts(accountsFromCustomerDtoReqObj, customerId);
        Customer updatedCustomer = CustomerMapper.mapToCustomer(customerDto);
        accountsRepository.save(accountsToBeUpdated);
        customerRepository.save(updatedCustomer);
        return new ResponseEntity<>(CustomerMapper.mapToCustomerDto(updatedCustomer), HttpStatus.CREATED);
    }

    /**
     * This method will create a new account for the provided customer details.
     * @param customer {@link Customer}
     */
    private Accounts createNewAccount(Customer customer) {
        Accounts accountToBeSaved = Accounts.builder()
                .accountNumber(generateNewAccountNumber())
                .accountType(AccountsContants.ACCOUNT_TYPE_SAVING)
                .branch(AccountsContants.BRANCH_ADDRESS)
                .customerId(customer.getCustomerId())
                .build();
        return accountToBeSaved;
    }

    /**
     * This method will generate the account number.
     * @return newly generated acc num
     */
    private long generateNewAccountNumber() {
        return 1000000000L + new Random().nextInt(900000000);
    }
}
