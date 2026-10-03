package org.sid.bank_account_service.web;

import org.sid.bank_account_service.DTO.BankAccountRequestDTO;
import org.sid.bank_account_service.DTO.BankAccountResponseDTO;
import org.sid.bank_account_service.service.AccountService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BancAccountGraphQlController {

    private final AccountService accountService;

    // 💡 Injection propre de AccountService via le constructeur
    public BancAccountGraphQlController(AccountService accountService) {
        this.accountService = accountService;
    }

    @QueryMapping
    public List<BankAccountResponseDTO> accountsList() {
        return accountService.listAccounts();
    }

    @QueryMapping
    public BankAccountResponseDTO getAccountById(@Argument String id) {
        return accountService.getAccount(id);
    }

    @MutationMapping
    public BankAccountResponseDTO createAccount(@Argument BankAccountRequestDTO bankAccount) {
        return accountService.saveAccount(bankAccount);
    }

    @MutationMapping
    public BankAccountResponseDTO updateAccount(@Argument String id, @Argument BankAccountRequestDTO bankAccount) {
        return accountService.updateAccount(id, bankAccount);
    }

    @MutationMapping
    public Boolean deleteAccount(@Argument String id) {
        accountService.deleteAccount(id);
        return true;
    }
}