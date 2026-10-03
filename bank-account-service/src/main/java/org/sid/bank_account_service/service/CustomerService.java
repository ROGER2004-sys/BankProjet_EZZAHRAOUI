package org.sid.bank_account_service.service;

import org.sid.bank_account_service.DTO.CustomerRequestDTO;
import org.sid.bank_account_service.DTO.CustomerResponseDTO;

import java.util.List;

public interface CustomerService {
    CustomerResponseDTO saveCustomer(CustomerRequestDTO customerRequestDTO);
    CustomerResponseDTO getCustomerById(Long id);
    List<CustomerResponseDTO> getAllCustomers();
    CustomerResponseDTO updateCustomer(Long id, CustomerRequestDTO customerRequestDTO);
    Boolean deleteCustomer(Long id);
}