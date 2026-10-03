package org.sid.bank_account_service.mappers;

import org.sid.bank_account_service.DTO.BankAccountResponseDTO;
import org.sid.bank_account_service.DTO.CustomerRequestDTO;
import org.sid.bank_account_service.DTO.CustomerResponseDTO;
import org.sid.bank_account_service.entities.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CustomerMapper {

    public CustomerResponseDTO fromCustomer(Customer customer) {
        if (customer == null) return null;
        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        BeanUtils.copyProperties(customer, customerResponseDTO);

        // Mapping de la liste des comptes
        if (customer.getBankAccounts() != null) {
            customerResponseDTO.setBankAccounts(
                    customer.getBankAccounts().stream().map(account -> {
                        BankAccountResponseDTO dto = new BankAccountResponseDTO();
                        BeanUtils.copyProperties(account, dto);
                        return dto;
                    }).collect(Collectors.toList())
            );
        }
        return customerResponseDTO;
    }

    public Customer fromCustomerRequestDTO(CustomerRequestDTO dto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(dto, customer);
        return customer;
    }
}