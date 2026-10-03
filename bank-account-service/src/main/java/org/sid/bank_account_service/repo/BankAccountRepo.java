package org.sid.bank_account_service.repo;

import org.sid.bank_account_service.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource

public interface BankAccountRepo extends JpaRepository<BankAccount, String> {

}
