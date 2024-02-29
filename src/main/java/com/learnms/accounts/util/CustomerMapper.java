package com.learnms.accounts.util;

import com.learnms.accounts.dto.CustomerDto;
import com.learnms.accounts.entity.Customer;

/**
 * Mapper class to create customer entity obj from customerDto or vice-versa.
 */
public class CustomerMapper {

    /**
     * Method to map customer entity to it's DTO.
     * @param customer {@link Customer}
     * @return {@link CustomerDto}
     */
    public static CustomerDto mapToCustomerDto(Customer customer) {
        return CustomerDto.builder()
                .email(customer.getEmail())
                .name(customer.getName())
                .mobileNumber(customer.getMobile())
                .build();
    }

    /**
     * Method to map customer DTO to customer entity.
     * @param customerDto {@link CustomerDto}
     * @return {@link Customer}
     */
    public static Customer mapToCustomer(CustomerDto customerDto) {
        return Customer.builder()
                .name(customerDto.getName())
                .mobile(customerDto.getMobileNumber())
                .email(customerDto.getEmail())
                .build();
    }
}
