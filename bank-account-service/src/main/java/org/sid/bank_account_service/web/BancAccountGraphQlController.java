package org.sid.bank_account_service.web;

import org.sid.bank_account_service.DTO.BankAccountRequestDTO;
import org.sid.bank_account_service.DTO.BankAccountResponseDTO;
import org.sid.bank_account_service.DTO.CustomerRequestDTO;
import org.sid.bank_account_service.DTO.CustomerResponseDTO;
import org.sid.bank_account_service.service.AccountService;
import org.sid.bank_account_service.service.CustomerService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BancAccountGraphQlController {

    private final AccountService accountService;
    private final CustomerService customerService;

    // 1. Injection des DEUX services dans le constructeur
    public BancAccountGraphQlController(AccountService accountService, CustomerService customerService) {
        this.accountService = accountService;
        this.customerService = customerService;
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


    @QueryMapping
    public List<CustomerResponseDTO> customerList() {
        return customerService.getAllCustomers();
    }

    @QueryMapping
    public CustomerResponseDTO getCustomerById(@Argument Long id) {
        return customerService.getCustomerById(id);
    }

    @MutationMapping
    public CustomerResponseDTO saveCustomer(@Argument CustomerRequestDTO customer) {
        return customerService.saveCustomer(customer);
    }

    @MutationMapping
    public CustomerResponseDTO updateCustomer(@Argument Long id, @Argument CustomerRequestDTO customer) {
        return customerService.updateCustomer(id, customer);
    }

    @MutationMapping
    public Boolean deleteCustomer(@Argument Long id) {
        return customerService.deleteCustomer(id);
    }


}