package com.learnms.accounts.repository;

import com.learnms.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Customer repository for performing crud operations in customer table;
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /**
     * JPA method to retrieve customer record for the provided mob num.
     * @param mobileNumber for which record need to be search
     * @return {@link Customer}
     */
    Optional<Customer> findByMobile(String mobileNumber);


}
