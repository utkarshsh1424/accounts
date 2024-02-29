package com.learnms.accounts.repository;

import com.learnms.accounts.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Accounts repository for performing crud operations in accounts table;
 */
@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {
}
