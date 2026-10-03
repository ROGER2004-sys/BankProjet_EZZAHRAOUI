package org.sid.bank_account_service.repo;

import org.sid.bank_account_service.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

}
