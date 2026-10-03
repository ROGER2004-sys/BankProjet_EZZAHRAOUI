package org.sid.bank_account_service.service;

import org.sid.bank_account_service.DTO.CustomerRequestDTO;
import org.sid.bank_account_service.DTO.CustomerResponseDTO;
import org.sid.bank_account_service.entities.Customer;
import org.sid.bank_account_service.mappers.CustomerMapper;
import org.sid.bank_account_service.repo.CustomerRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepository;
    private final CustomerMapper customerMapper;

    // Injection des deux dépendances via le constructeur
    public CustomerServiceImpl(CustomerRepo customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    @Transactional
    public CustomerResponseDTO saveCustomer(CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerMapper.fromCustomerRequestDTO(customerRequestDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.fromCustomer(savedCustomer);
    }

    @Override
    public CustomerResponseDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        return customerMapper.fromCustomer(customer);
    }

    @Override
    public List<CustomerResponseDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customerMapper::fromCustomer)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponseDTO updateCustomer(Long id, CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

        if (customerRequestDTO.getName() != null) {
            customer.setName(customerRequestDTO.getName());
        }

        Customer updatedCustomer = customerRepository.save(customer);
        return customerMapper.fromCustomer(updatedCustomer);
    }

    @Override
    public Boolean deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Customer not found with id: " + id);
        }
        customerRepository.deleteById(id);
        return true;
    }
}