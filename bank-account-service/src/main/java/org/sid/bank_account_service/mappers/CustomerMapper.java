package org.sid.bank_account_service.mappers;

import org.sid.bank_account_service.DTO.CustomerRequestDTO;
import org.sid.bank_account_service.DTO.CustomerResponseDTO;
import org.sid.bank_account_service.entities.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    // Conversion pour la réponse API (Entité -> ResponseDTO)
    public CustomerResponseDTO fromCustomer(Customer customer) {
        if (customer == null) return null;
        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        BeanUtils.copyProperties(customer, customerResponseDTO);
        return customerResponseDTO;
    }

    // Conversion pour la création/modification (RequestDTO -> Entité)
    public Customer fromCustomerRequestDTO(CustomerRequestDTO customerRequestDTO) {
        if (customerRequestDTO == null) return null;
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerRequestDTO, customer);
        return customer;
    }
}